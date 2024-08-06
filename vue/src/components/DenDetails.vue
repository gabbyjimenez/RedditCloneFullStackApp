<template>
  <div>
    <label for="">Search: </label>
    <input id="searchBar" type="text" name="denName" v-model="searchFilter" />
  </div>


  <add-den-form />

  <div id="divCards">
     <add-den-form />
    <div class="card" style="width: 100;" v-for="den in filteredDens" v-bind:key="den.denName">
      <div id="cards" class="card-body" v-on:click="
        $router.push({ name: 'den', params: { denName: den.denName } })
        ">
        <h5 class="card-title">{{ den.denName }} {{ den.denCreatorUserName }}</h5>
        <p2 class="card-subtitle mb-2 text-muted">User ID: {{ den.denId }}</p2>
        <p class="card-text">{{den.denDesc}}</p>
              </div>
              <button v-on:click="DenService.delete(this.den)">delete</button>
            </div>
  </div>
</template>
  
<script>
import { setBlockTracking } from 'vue';
import DenService from '../services/DenService';


export default {
  components: {
  },


  data() {
    return {
      searchFilter: "",
    };
  },
  computed: {
    filteredDens() {

      const searchFilter = this.searchFilter.toLowerCase();
      return this.$store.state.dens.filter((den) => {
        // Check if denName includes the searchFilter
        const nameMatch = den.denName.toLowerCase().includes(searchFilter);

        // Check if any categoryName includes the searchFilter
        const categoryMatch = den.categoryNames.some((category) =>
          category.toLowerCase().includes(searchFilter)
        );

        // Return true if either condition matches or searchFilter is empty
        return searchFilter === "" ? true : nameMatch || categoryMatch;
      });
    },

  },
  methods: {
    DeleteDen(den) {
      console.log("bleep")
      if (confirm("Are you sure you want to delete this message? This action cannot be undone.")) {

        // TODO - Do a delete, then navigate to Topic Details on success
        // For errors, call handleErrorResponse
        console.log(den)
        console.log("blorg")

        DenService.delete(den).then(response => {
          console.log("deleted")
        }).catch(error => {
          this.handleErrorResponse(error, 'deleting');
        })

      }
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



}

#divCards {
  display: flex;
  flex-direction: column;
  padding: auto;
  margin: 10%;
  margin-top: 1%;

  flex-wrap: nowrap;
  justify-content: space-around;
  
}

</style>

