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

    <ul id="denList">
      <div v-if="!showFavoriteDens">
        <li v-for="den in filteredDens" :key="den.denName" class="den-item">
          <div class="main-container" @click="$router.push({ name: 'den', params: { denName: den.denName } })">
            <div id="denHeader">
              <img id="denPic" class="img-fluid img-responsive rounded-circle" 
                src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723478237/Designer_4_kr6i4y.jpg" width="38">
                <p id="userName" class="den-meta">Created by: {{ den.denCreatorUserName }}</p>
              <div class="header-content">
                <h5 id="denTitle">{{ den.denName }}</h5>
              </div>
            </div>

            <i id="favoriteIcon" class="fa-solid fa-star"
              :class="{ 'favorited': isFav(den) }"
              v-on:click.stop="toggleFavorite(den)"></i>

            <div id="denDescription" class="card-body">
              <p>{{ den.denDesc }}</p>
              <i v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
                @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon"> </i>
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
              <div class="header-content">
                <h5 id="denTitle">{{ den.denName }}</h5>
              </div>
            </div>
            <i id="favoriteIcon" class="fa-solid fa-star"
              :class="{ 'favorited': isFav(den) }"
              v-on:click.stop="toggleFavorite(den)"></i>

            <div id="denDescription" class="card-body">
              <p>{{ den.denDesc }}</p>
              <i v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
                @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon"> </i>
            </div>
          </div>
        </li>
      </div>
    </ul>
  </div>
</template>


<script>
import DenService from '../services/DenService';

export default {
  data() {
    return {
      searchFilter: "",
      showFavoriteDens: false,
      favorites: []
    };
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
<<<<<<< HEAD
=======
    this.getFavorites(this.$store.state.user);
>>>>>>> e8b1a0b1b47db093e122cc5d6cdc64a24cf392f0
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

#denHeader {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #e6e6e6;
  background-color: #f9f9f9;
  position: relative;
}

#denPic {
  border-radius: 50%;
  border: 2px solid #ccc;
  margin-right: 10px;
}

.header-content {
  flex: 1;
  display: flex;
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
  font-size: 18px;
  font-weight: bold;
  color: #333;
  text-align: center;
<<<<<<< HEAD
  margin: 0; /* Remove default margins */
=======
  margin-right: 15%; /* Remove default margins */
>>>>>>> e8b1a0b1b47db093e122cc5d6cdc64a24cf392f0
}

#denDescription {
  padding: 10px;
}

.container {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
<<<<<<< HEAD
=======
  align-items: center;
>>>>>>> e8b1a0b1b47db093e122cc5d6cdc64a24cf392f0
}

/* Search Bar Styling */
#searchBar {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

#searchBar input {
  width: 100%;
  max-width: 600px;
  padding: 10px;
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
  position: relative;
  cursor: pointer;
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
  position: absolute;
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
  position: absolute;
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
  flex-direction: row;
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
  padding-left: 10%;
  padding-right: -10%;
}

#flexSwitchCheckLabel {
  flex-direction: flex-start;
}

#favoriteToggle {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  font-size: larger;




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
