<template>
  <div id="home" class="home">
    <theHeader id="header" />
    <div id="body">
    <AddDenForm/>
    <DenDetails id="dendetails" v-bind:dens="dens"/>
    </div>
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
     }
  },

  methods: {

    getDens() {
      DenService.getDens().then(response => {
        this.$store.state.dens = response.data
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
#body {
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  
}

</style>
