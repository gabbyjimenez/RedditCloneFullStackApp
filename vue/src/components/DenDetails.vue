<template>
  <div class="container">
    <div id="searchBar">
      <input type="search" class="form-control rounded" placeholder="Search dens or categories" aria-label="Search" v-model="searchFilter" />
    </div>

    <ul id="denList">
      <li v-for="den in filteredDens" :key="den.denName">
        <div class="card">
          <div class="card-header">
            <h5>{{ den.denName }}</h5>
            <p>{{den.isFavorite}}</p>
            <p>Created by: {{ den.denCreatorUserName }}</p>
          </div>
          <div class="card-body" v-on:click="$router.push({ name: 'den', params: { denName: den.denName } })">
            <p>{{ den.denDesc }}</p>
          </div>
          <button class="delete-button" v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')" v-on:click="DeleteDen(den)"></button>
        </div>
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
  


<style>




#cards {

  display: flex;
  flex-direction: column;
  text-align: center;
  margin: 1%;
  font-size: larger;
  box-shadow: 10px 5px 5px rgba(0, 0, 0, 0.207), 1px 0 .4em rgba(0, 0, 0, 0.366);
  


}

#divCards {
  display: flex;
  flex-direction: column;
  margin: 10%;
  margin-top: 1%;
  flex-wrap: nowrap;
  justify-content: space-around;

}

#button {
  display: flex;
  flex-direction: row;
  width: 30%;
  justify-content: center;

}
#searchBar {
display: flex;
width: 30%;
justify-content: center;
margin: auto;
}
#deleteButton {
  display: flex;
width: 8%;
margin: auto;
margin-top: 0.1%;
margin-bottom: 0.1%;
justify-content: center;

}

#searchBar {
  display: flex;
width: 20%;
margin: auto;
margin-top: 0.1%;
margin-bottom: 0.1%;
justify-content: center;
color: aqua;
}
</style>

