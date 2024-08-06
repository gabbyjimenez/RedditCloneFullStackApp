<template>
  <div class="home">
    <theHeader id="header" />
    <AddDenForm />
    <DenDetails id="dendetails" v-bind:dens="dens"/>
    
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>

import theHeader from '../components/Header.vue'
import DenDetails from '../components/DenDetails.vue'
import DenService from '../services/DenService.js'
import AddDenForm from '../components/AddDenForm.vue'



export default {
  components: {
    DenDetails,
    theHeader,
    AddDenForm
},

  data() {
    return {
      dens: []
    }
  },

  methods: {

    getDens() {
      DenService.getDens().then(response => {
        this.dens = response.data
      }).catch(error => {
        console.log('You are out of luck')
      })
    }
  },
  created() {
    this.getDens();
  }
}
</script>

<style>
.home {
  text-align: center;

}
</style>
