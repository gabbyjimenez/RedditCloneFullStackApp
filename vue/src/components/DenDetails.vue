<template>
    <div>
        <label for="">Search Player: </label>
        <input type="text" name="denName" id="denName" v-model="searchFilter">
    </div>

    <div>
        <h4>Explore Dens</h4>
        <div class="den" v-for="den in filteredDens" v-bind:key="den.denId" >
        <p v-on:click="$router.push({ name: 'den', params: { denId: den.denId } })">{{ den.denId }} : {{ den.denName }} - {{ den.denCreatorUserName }}</p>
        </div>
    </div>
</template>
  
<script>

import DenService from '../services/DenService.js';

export default {

    data() {
        return {
            searchFilter: '',
            dens: [

            ]
        }
    },
    computed: {
        filteredDens() {


            return this.dens.filter((den) => {
                return this.searchFilter == '' ? true : den.denName.includes(this.searchFilter);
            });



        }
    },
    created() {

        DenService.getDens().then(response => {
            this.dens = response.data;
        }).catch(error => {
            console.log('You are out of luck')
        })

    }

}
</script>
  
<style>
</style>