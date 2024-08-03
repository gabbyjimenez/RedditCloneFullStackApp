<template>
  <div class="home">
    <Header/>
    <side-bar/>
    //sidebar
    //header
    <DenDetails />
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>

import Header from '../components/Header.vue'
import DenDetails from '../components/DenDetails.vue'
import SideBar from '../components/SideBar.vue'


export default {
  components: {
    DenDetails,
    SideBar,
    Header
  }
}
</script>

<style>

.home{
  text-align: center;
}

</style>
