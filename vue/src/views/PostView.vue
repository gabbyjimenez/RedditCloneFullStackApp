<template>
    <theHeader/>
    <SideBar/>
    <PostDeails/>

  
</template>

<script>


import theHeader from '../components/Header.vue'
import SideBar from '../components/SideBar.vue'
import PostDeails from '../components/PostDeails.vue';

export default {
    components: {
    theHeader,
    SideBar,
    PostDeails
}

}
</script>

<style>

</style>