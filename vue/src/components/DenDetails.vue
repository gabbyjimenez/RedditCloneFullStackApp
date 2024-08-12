<template>

  <div id="searchBar">
      <input type="search" class="form-control rounded" placeholder="Search dens or catagories" Segoe-label="Search"
         v-model="searchFilter" />
  </div>



  <li>
    <div class="card" style="width: 100;" v-for="den in filteredDens" v-bind:key="den.denName">
      <div id="cards" class="card-body" v-on:click="
        $router.push({ name: 'den', params: { denName: den.denName } })
        ">
        <h5>{{ den.denName }} </h5>
      
        <p>created by: {{ den.denCreatorUserName }}</p>
        <p>{{ den.denDesc }}</p>
        <button id="deleteButton" v-if="den.denCreatorUserName == $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')" v-on:click="DeleteDen(den)">delete</button>
      </div>
      <div>

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

