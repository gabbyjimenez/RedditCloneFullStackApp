<template>
  <div class="container">
    <div id="searchOption">
      <div id="searchBar">
        <input id="searchBar" type="search" class="form-control" placeholder="Search dens or categories"
          aria-label="Search" v-model="searchFilter" />
          </div>
         
      <div id="favoriteToggle" class="form-check form-switch">
        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault"
          v-on:click="toggleFavoriteDens">
        <div class="form-check-label" for="flexSwitchCheckDefault" id="flexSwitchLabel">Followed Dens</div>
      </div>
    </div>
    <div id="denBody">
    <ul id="denList">
      <div id="filtered" v-if="!showFavoriteDens">
        <li v-for="den in filteredDens" :key="den.denName" class="den-item">
          <div class="main-container" @click="$router.push({ name: 'den', params: { denName: den.denName } })">
            
            <div id="denHeader">
              <img id="denPic" class="img-fluid img-responsive rounded-circle" 
                src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723478237/Designer_4_kr6i4y.jpg" width="38">
                <p id="userName" class="den-meta">Created by: {{ den.denCreatorUserName }}</p>

                <div id="categoryIconAndTrash">
                  <CategoryBadge
                    v-for="(category, index) in den.categoryNames"
                    :key="index"
                    :category="category"
                  />
                  <i id="favoriteIcon" class="fa-solid fa-star"
              :class="{ 'favorited': isFav(den) }"
              v-on:click.stop="toggleFavorite(den)" style="margin-left:5px;margin-right:5px"></i>

              <i v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
                  @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon" style="margin-left:5px;margin-right:5px"> </i>

                </div>

                  <div class="header-content">
                <h5 id="denTitle">{{ den.denName }}</h5>
              </div>
            </div>

            <div id="denDescription" class="card-body">
              <p>{{ den.denDesc }}</p>
            </div>

          </div>
        </li>
      </div>
      <div v-else>
        <li v-for="den in filteredFavdens" :key="den.denName" class="den-item">
          <div class="main-container" @click="$router.push({ name: 'den', params: { denName: den.denName } })">
            
            <div id="denHeader">
              <img id="denPic" class="img-fluid img-responsive rounded-circle" 
                src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723478237/Designer_4_kr6i4y.jpg" width="38">
                <p id="userName" class="den-meta">Created by: {{ den.denCreatorUserName }}</p>

                <div id="categoryIconAndTrash">
                  <CategoryBadge
                    v-for="(category, index) in den.categoryNames"
                    :key="index"
                    :category="category"
                  />
                  <i id="favoriteIcon" class="fa-solid fa-star"
                    :class="{ 'favorited': isFav(den) }"
                    v-on:click.stop="toggleFavorite(den)" style="margin-left:5px;margin-right:5px"></i>

                  <i v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
                      @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon" style="margin-left:5px;margin-right:5px"> </i>

                </div>

                  <div class="header-content">
                <h5 id="denTitle">{{ den.denName }}</h5>
              </div>
            </div>

            <div id="denDescription" class="card-body">
              <p>{{ den.denDesc }}</p>
            </div>

          </div>
        </li>
      </div>
    </ul>
  </div>
</div>
</template>


<script>
import DenService from '../services/DenService';
import CategoryBadge from './CategoryBadge.vue';

export default {
  data() {
    return {
      searchFilter: "",
      showFavoriteDens: false,
      favorites: []
    };
  },
  components: {
      CategoryBadge
  },
  computed: {
    filteredDens() {
      const searchFilter = this.searchFilter.toLowerCase();
      return this.$store.state.dens.filter((den) => {
        const nameMatch = den.denName.toLowerCase().includes(searchFilter);
        const categoryMatch = den.categoryNames.some((category) =>
          category.toLowerCase().includes(searchFilter)
        );
        return searchFilter === "" ? true : nameMatch || categoryMatch;
      }).sort((a, b) => {
        if (a.isFavorite && !b.isFavorite) return -1;
        if (!a.isFavorite && b.isFavorite) return 1;
        return 0;
      });
    },

    filteredFavdens() {
      return this.$store.state.dens.filter((den) => 
        this.favorites.some(fav => fav.denId === den.denId)
      ).sort((a, b) => {
        if (a.isFavorite && !b.isFavorite) return -1;
        if (!a.isFavorite && b.isFavorite) return 1;
        return 0;
      });
    },
  },

  methods: {
    isFav(den) {
      return this.favorites.some(fav => fav.denId === den.denId);
    },

    toggleFavoriteDens() {
      this.showFavoriteDens = !this.showFavoriteDens;
      this.getFavorites(this.$store.state.user);
    },

    DeleteDen(den) {
      if (confirm("Are you sure you want to delete this message? This action cannot be undone.")) {
        DenService.delete(den).then(() => {
          this.getDens();
        }).catch(error => {
          this.handleErrorResponse(error, 'deleting');
        });
      }
    },

    getDens() {
      DenService.getDens().then(response => {
        this.$store.state.dens = response.data;
      }).catch(error => {
        console.log('You are out of luck');
      });
    },

    getFavorites(user) {
      DenService.getFavorites(user)
        .then(response => {
          this.favorites = response.data;
        })
        .catch(error => {
          console.log("You are out of luck");
        });
    },

    toggleFavorite(den) {
      DenService.toggleFavorite(den)
        .then(() => {
          this.getFavorites(this.$store.state.user);
        })
        .catch(error => {
          console.log("You are out of luck");
        });
    },
  },
  created(){
    this.getFavorites(this.$store.state.user);
    this.getDens()
  }
}
</script>

<style scoped>
/* General Container Styling */
.main-container {
  display: flex;
  flex-direction: column;
  width: 80%;
  position: relative;
  border: 1px solid #e6e6e6;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: box-shadow 0.3s ease;
  background-color: #ffffff;
}

.main-container:hover {
  transform: scale(1.02); /* Slightly enlarge the container on hover */
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3); /* Stronger shadow effect */
  cursor: pointer;
}

#categoryIconAndTrash {
  display: flex;
  align-items: center;
  margin-left: auto; /* ALIGNS ITEMS TO THE RIGHT OF THE HEADER BLOCK*/
}

#denHeader {
  padding-top: 10px;
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #e6e6e6;
  background-color: #f9f9f9;
  position: relative;
  flex-wrap: wrap; /* ALLOWS DEN TITLE TO GO TO A NEW LINE TO BE CENTERED PROPERLY*/
}

#denPic {
  border-radius: 50%;
  border: 2px solid #ccc;
  margin-right: 10px;
}

.header-content {
  flex: 1 1 100%;  /* MAKES DEN TITLE TAKE 100% of SPACE SO THAT IT FORCES TO A NEW LINE */
  flex-direction: column;
  justify-content: center; /* Center vertically within available space */
  align-items: center; /* Center horizontally */
}

#userName {
  font-size: 14px;
  color: #555;
  margin: 0; /* Remove default margins */
  text-align: center;
}

#denTitle {
  font-size: 22px; /* DEN NAME SIZE*/
  font-weight: bold;
  color: #333;
  text-align: center;
  margin: 0; /* Remove default margins */

}

#denDescription {
  font-size:18px; /* DEN DESCRIPTION SIZE*/
  padding: 10px;
}

.container {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 1200px; /* Optional: sets a maximum width for better responsiveness */
  position: relative;
  overflow: hidden;
  transition: box-shadow 0.3s ease;
  background-color: #ffffff;
}

#body{
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  flex: 1; /* Take up remaining space */
  padding: 20px; /* Add some padding */
  box-sizing: border-box; /* Include padding in width calculation */
}

/* Search Bar Styling */
#searchBar {
  display: flex;
  justify-content: center;
}

#searchBar input {
  width: 100%;
  max-width: 600px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

/* Favorite Toggle Styling */
#favoriteToggle {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.form-check-label {
  margin-left: 10px;
  font-size: 14px;
  color: #555;
}

/* Den List Styling */
#denList {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.den-item {
  margin-bottom: 20px;
  display: flex;
  justify-content: center; /* Center the main container */
}

.den-item:hover .main-container {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.card-body {
  padding: 15px;
}

.den-meta {
  font-size: 14px;
  color: #666;
}

/* Favorite Icon Styling */
#favoriteIcon {
  top: 10px;
  right: 40px;
  color: #b6b6b6;
  font-size: 20px;
  cursor: pointer;
  transition: color 0.3s ease;
}

#favoriteIcon.favorited {
  color: #fe7f2d;
}

#favoriteIcon:hover {
  color: #ffab00;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

/* Trash Can Icon Styling */
#trashCanIcon {
  top: 10px;
  right: 10px;
  font-size: 20px;
  color: red;
  cursor: pointer;
}

 

.fa-solid:hover {
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

#favoriteToggle {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  text-wrap: 1;
  width: 100%;
}

#flexSwitchCheckDefault {
  
  display: flex;
  font-size: large;

}



#flexSwitchLabel {
  display: flex;
  height:50%;
  width: 10%;
  justify-content: flex-start
}

#searchOption {
  display: flex;
  flex-direction: column;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
width: 100%;
 align-content: center;
  align-items: center;
  text-align: center;


}

#searchBar {
  display: flex;
  width: 90%;
  align-items: center;
  text-align: center;
  margin-bottom: auto;

}

#flexSwitchCheckLabel {
  flex-direction: flex-start;
}

#favoriteBox {
  display: flex;
  color: gold;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  font-size: small;
  margin-top: auto;
  margin-bottom: auto;
  width: 10%;
  
 
}

</style>
