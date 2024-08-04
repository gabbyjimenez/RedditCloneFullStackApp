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
.den {
    border-bottom: 1px solid #f2f2f2;
}

.den:last-child {
    border-bottom: 0px;
}

.den a {
    display: block;
    padding: 10px 20px;
}

.den a:link,
.den a:visited {
    color: black;
    text-decoration: none;
}

.den a:hover {
    background-color: #f2f2f2;
    cursor: pointer;
}


.den {
  background: rgb(245, 245, 245);
  border: solid 2px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  width: 50%;
  margin-bottom: 10px;
  padding: 10px;
  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.8);
  word-wrap: break-word;
  cursor: pointer;
}
</style>