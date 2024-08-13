<template>
  <div class="container">
    <div id="searchBar">
      <input type="search" class="form-control" placeholder="Search dens or categories" aria-label="Search"
        v-model="searchFilter" />
    </div>

    <ul id="denList">
      <li v-for="den in filteredDens" :key="den.denName" class="den-item">

        <div class="main-container" @click="$router.push({ name: 'den', params: { denName: den.denName } })">
          <div id="denHeader">
            <img id="denPic" class="img-fluid img-responsive rounded-circle mr-2"
              src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723478237/Designer_4_kr6i4y.jpg" width="38">

            <p id="userName" class="den-meta">Created by: {{ den.denCreatorUserName }}</p>
          </div>
          <h5 id="denTitle" class="mr-2">{{ den.denName }}</h5><span class="dot mb-1"></span>

          <!-- <p class="den-meta">Favorite: {{ den.isFavorite ? 'Yes' : 'No' }}</p> -->
          <i id="favoriteIcon" class="fa-solid fa-star"></i>

          <div id="denDescription" class="card-body" >
            <p>{{ den.denDesc }}</p>

            <i v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
              @click.stop="DeleteDen(den)" class="fa-solid fa-trash trashCan" id="trashCanIcon"> </i>



          </div>

        </div>


















        <!-- 
        <div class="card">
          <div class="card-header">
            <h5>{{ den.denName }}</h5>
            <p class="den-meta">Created by: {{ den.denCreatorUserName }}</p>
            <p class="den-meta">Favorite: {{ den.isFavorite ? 'Yes' : 'No' }}</p>
          </div>
          <div class="card-body" @click="$router.push({ name: 'den', params: { denName: den.denName } })">
            <p>{{ den.denDesc }}</p>
            <button class="delete-button"
              v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')"
              @click.stop="DeleteDen(den)">&#x2716;</button>
          </div> -->
        <!-- </div> -->
      </li>
    </ul>
  </div>
</template>



<script>
import DenService from '../services/DenService';

export default {
  data() {
    return {
      searchFilter: "",
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
    }
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
#denTitle{
  font-weight: bolder;
}
#denDescription{
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
  right: 10px;
  background: transparent;
  color: #dc3545;
  border: none;
  font-size: 20px;
  cursor: pointer;
  transition: color 0.3s ease;
}
#favoriteIcon{ 
  position: absolute;
  top: 10px;
  right: 35px;
  background: transparent;
  color: #dc3545;
  border: none;
  font-size: 20px;
  cursor: pointer;
  transition: color 0.3s ease;
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
</style>