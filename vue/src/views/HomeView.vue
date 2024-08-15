<template>
  <div id="page">
  <div id="Dens" class="Dens">
    <theHeader id="header" />
    <div id="body">
    <AddDenForm v-if="this.$store.state.user.userId !=0"/>

    <DenDetails />
    </div>
    <!-- <p>You must be authenticated to see this</p> -->
  </div>
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
    show(){
      console.log(this.$store.state.user)
    },

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
    this.show();
    console.log("created")
  }
}
</script>

<style scoped>
#page{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
#body {
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  
}

</style>
