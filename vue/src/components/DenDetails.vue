<template>
    <div>
        <label for="">Search: </label>
        <input type="text" name="denName" v-model="searchFilter">
    </div>

    <div>
        <h4>Explore Dens</h4>
        <div class="den" v-for="den in filteredDens" v-bind:key="den.denName" >
        <p v-on:click="$router.push({ name: 'den', params: { denName: den.denName } })">{{ den.denId }} : {{ den.denName }} - {{ den.denCreatorUserName }}</p>
        </div>
    </div>
</template>
  
<script>
import DenList from './DenList.vue';



export default {

    props: {
       dens: {
        type: Array,
        
       }

    },
    
    data() {
        return {
            searchFilter: ''
        }
    },
    computed: {
        filteredDens() {
            return this.dens.filter((den) => {
                return this.searchFilter == '' ? true : den.denName.includes(this.searchFilter) || den.categoryNames.includes(this.searchFilter);
            });



        }
    },
  

}
</script>
  
<style>
</style>