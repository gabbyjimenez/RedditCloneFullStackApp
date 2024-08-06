<template>
  <div>
    <label for="">Search: </label>
    <input type="text" name="denName" v-model="searchFilter" />
  </div>

  <!-- <add-den-form/> -->
  <!-- <div>
    <h4>Explore Dens</h4>
    <div class="den" v-for="den in filteredDens" v-bind:key="den.denName">
      <p
        v-on:click="
          $router.push({ name: 'den', params: { denName: den.denName } })
        "
      >
        {{ den.denId }} : {{ den.denName }} - {{ den.denCreatorUserName }}
      </p>
    </div>
  </div> -->
  <div  class="card" style="width: 18rem;" v-for="den in filteredDens" v-bind:key="den.denName">
  <div id = "cards" class="card-body"  v-on:click="
          $router.push({ name: 'den', params: { denName: den.denName } })
        ">
    <h5 class="card-title" >{{ den.denName }} {{ den.denCreatorUserName }}</h5>
    <p2 class="card-subtitle mb-2 text-muted">User ID: {{ den.denId }}</p2>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  
  </div>
</div>
</template>
  
<script>
// import AddDenForm from './AddDenForm.vue';


export default {
  components: { 
    // AddDenForm 
  },
  // props: {
  //   dens: {
  //     type: Array,
  //   },
  // },

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
};
</script>
  
<style>
.card{
  display: flex;
justify-content: center;
background-color: rgba(222, 222, 222, 0.504);

}

</style>