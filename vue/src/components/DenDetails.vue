<template>
  <div>
    <label for="">Search: </label>
    <input type="text" name="denName" v-model="searchFilter" />
  </div>

  <!-- <add-den-form/> -->

  <div>
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
</style>