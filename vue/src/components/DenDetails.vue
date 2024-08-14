<template>
  <div class="container">
    <div id="searchOption">
      <div id="searchBar">
<div id="searchBar">
        <input id="searchText" type="search" class="form-control" placeholder="Search dens or categories"
          aria-label="Search" v-model="searchFilter" />
          </div>
          <div id="favoriteBox">
        <div id="favoriteToggle" class="form-check form-switch">

          <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault"
            v-on:click="(showFavoriteDens = !showFavoriteDens); getFavorites(this.$store.state.user)">
        </div>
        <div id="flexSwitchCheckLabel">
          <p>Favorites</p>
        </div>
      </div>
      </div>
      
    </div>

    <ul id="denList">
      <li v-for="den in filteredDens" :key="den.denName" class="den-item" v-show="!showFavoriteDens">

        <div class="main-container" @click="$router.push({ name: 'den', params: { denName: den.denName } })">
          <div id="denHeader">
            <img id="denPic" class="img-fluid img-responsive rounded-circle mr-2"
              src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723478237/Designer_4_kr6i4y.jpg" width="38">

            <p id="userName" class="den-meta">Created by: {{ den.denCreatorUserName }}</p>
          </div>
          <h5 id="denTitle" class="mr-2">{{ den.denName }}</h5><span class="dot mb-1"></span>
         
          <i id="favoriteIcon" class="fa-solid fa-star" v-on:click.stop="toggleFavorite(den)"></i>

          <div id="denDescription" class="card-body">
            <p>{{ den.denDesc }}</p>

            <i v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
              @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon"> </i>
          </div>
        </div>
      </li>
      <li v-for="favorite in favorites" v-bind:key="favorite.denName" v-show="showFavoriteDens">
        <div class="main-container" @click="$router.push({ name: 'den', params: { denName: favorite.denName } })">
          <div id="denHeader">
            <img id="denPic" class="img-fluid img-responsive rounded-circle mr-2"
              src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723478237/Designer_4_kr6i4y.jpg" width="38">

            <p id="userName" class="den-meta">Created by: {{ favorite.denCreatorUserName }}</p>
          </div>
          <h5 id="denTitle" class="mr-2">{{ favorite.denName }}</h5><span class="dot mb-1"></span>

          <i id="favoriteIcon" class="fa-solid fa-star"></i>

          <div id="denDescription" class="card-body">
            <p>{{ favorite.denDesc }}</p>

            <i v-if="favorite.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
              @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon"> </i>
          </div>
        </div>

      </li>
    </ul>




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

  computed: {
    filteredDens() {
      const searchFilter = this.searchFilter.toLowerCase();

      // Filter dens based on searchFilter
      const filtered = this.$store.state.dens.filter((den) => {
        const nameMatch = den.denName.toLowerCase().includes(searchFilter);
        const categoryMatch = den.categoryNames.some((category) =>
          category.toLowerCase().includes(searchFilter)
        );
        return searchFilter === "" ? true : nameMatch || categoryMatch;
      });

      // Sort dens with isFavorite at the top
      return filtered.sort((a, b) => {
        if (a.isFavorite && !b.isFavorite) return -1;
        if (!a.isFavorite && b.isFavorite) return 1;
        return 0;
      });
    },
  },
  methods: {
    DeleteDen(den) {
      console.log("bleep");
      if (confirm("Are you sure you want to delete this message? This action cannot be undone.")) {
        DenService.delete(den).then(response => {
          this.getDens();
          console.log("deleted");
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
        .then((response) => {
          console.log(response.data)
          this.favorites = response.data;
        })
        .catch((error) => {
          console.log("You are out of luck");
        });
    },
    toggleFavorite(den) {
      DenService.toggleFavorite(den)
        .then((response) => {
          this.getFavorites(this.$store.state.user);

        })
        .catch((error) => {
          console.log("You are out of luck");
        });


    },
  }
};
</script>


<style scoped>
/* General Container Styling */
.main-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  position: relative;
  /* Required for absolute positioning of delete button */
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: box-shadow 0.3s ease;
}

#denHeader {
  display: flex;
  flex-direction: row;
  align-items: center;
  max-height: 5%;
  font-size: larger;

}

#userName {
  display: flex;
  align-items: center;

  margin-top: auto;
  margin-bottom: auto;
}

.container {
  width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Segoe UI';
}

/* Search Bar Styling */
#searchBar {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.form-control {
  width: 100%;
  max-width: 600px;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

/* Den List Styling */
#denList {
  list-style-type: none;
  padding: 0;
  margin: 0;
  width: 100
}

.den-item {
  margin-bottom: 20px;
  position: relative;
  /* Position relative to position the delete button absolutely */
}

#denTitle {
  font-weight: bolder;
}

#denDescription {
  font-weight: lighter;
}

/* Card Styling */
.card {
  position: relative;
  /* Required for absolute positioning of delete button */
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: box-shadow 0.3s ease;
  margin: auto;
}

.card:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.card-header {

  background-color: #f8f9fa;
  padding: 15px;
  border-bottom: 1px solid #ddd;
}

#desc-container {
  display: flex;
  flex-direction: column;
  width: 80%;

}

#denPic {
  max-height: 15%;
  padding: .5%;

}
#denTitle{
  font-size: xx-large;
}
#denDescription{
  font-size: large;
}

.card-body {
  padding: 15px;
  cursor: pointer;
}

.card-body p {
  margin: 0;
}

/* Meta Information Styling */
.den-meta {
  font-size: 14px;
  color: #555;
}

/* Delete Button Styling */
.fa-trash {
  position: absolute;
  top: 10px;
  right: 8px;
  background: transparent;
  color: #dc3545;
  border: none;
  font-size: 15px;
  cursor: pointer;
  transition: color 0.3s ease;
}

#favoriteIcon {
  position: absolute;
  top: 10px;
  right: 25px;
  background: transparent;
  color: #b6b6b6;
  font-size: 15px;
  cursor: pointer;
  transition: color 0.3s ease;
}

#favoriteIcon:hover {
  position: absolute;


  color: rgba(89, 89, 89, 0.379);

  cursor: pointer;
  transition: color 0.3s ease;
  animation-name: fa-fade;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}


.fa-trash:hover {
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
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
