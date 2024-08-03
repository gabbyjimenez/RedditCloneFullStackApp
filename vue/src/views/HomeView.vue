<template>
  <div class="home">
    <theHeader/>
    <side-bar/>
    //sidebar
    //header
    <DenDetails />
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>

import theHeader from '../components/Header.vue'
import DenDetails from '../components/DenDetails.vue'
import SideBar from '../components/SideBar.vue'


export default {
  components: {
    DenDetails,
    SideBar,
    theHeader
  }
}
</script>

<style>

.home{
  text-align: center;
}

</style>
