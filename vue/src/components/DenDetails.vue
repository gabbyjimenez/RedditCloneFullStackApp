<template>

  <div id="searchBar" >
      <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
        aria-describedby="search-addon" v-model="searchFilter" />
  </div>


  <add-den-form />

  <li id="divCards">
    <add-den-form />
    <div class="card" style="width: 100;" v-for="den in filteredDens" v-bind:key="den.denName">
      <div id="cards" class="card-body" v-on:click="
        $router.push({ name: 'den', params: { denName: den.denName } })
        ">
        <h5 class="card-title">{{ den.denName }} </h5>
      
        <p2 class="card-subtitle mb-2 text-muted">created by: {{ den.denCreatorUserName }}</p2>
        <p class="card-text">{{ den.denDesc }}</p>
      </div>
      <div id="button">
        <button id="button" v-on:click="DeleteDen(den)">delete</button>
      </div>
    </div>
  </li>

</template>
  
<script>
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
          this.getDens()
          console.log("deleted")
        }).catch(error => {
          this.handleErrorResponse(error, 'deleting');
        })

      }
      
    },
    getDens() {
      DenService.getDens().then(response => {
        this.$store.state.dens = response.data
      }).catch(error => {
        console.log('You are out of luck')
      })
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
</style>

