<template>
  <div class="home">
    <h1>Home</h1>
    <side-bar/>
    //sidebar
    //header
    <DenDetails />
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>

import DenDetails from '../components/DenDetails.vue'
import SideBar from '../components/SideBar.vue'


export default {
  components: {
    DenDetails,
    SideBar
  }
}
</script>

<style>

.home{
  text-align: center;
}

</style>
