<template>
  <div>
    <label for="">Search: </label>
    <input type="text" name="denName" v-model="searchFilter" />
  </div>

  <div>
    <h4>Explore Dens</h4>
    <div class="den" v-for="den in filteredDens" v-bind:key="den.denName">
      <p v-on:click="
        $router.push({ name: 'den', params: { denName: den.denName } })
        ">
        {{ den.denId }} : {{ den.denName }} - {{ den.denCreatorUserName }}
      </p>
      <button v-on:click="DeleteDen(den)">delete</button>

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
  
<style></style>