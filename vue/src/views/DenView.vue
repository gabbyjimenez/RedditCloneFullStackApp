<template>
    <div>
        <theHeader id="header" />
        <side-bar id="sidebar" /> 
        <PostList v-bind:posts="PostList" />     
    </div>
    

</template>

<script>

import theHeader from '../components/Header.vue'
import SideBar from '../components/SideBar.vue'
import PostService from '../services/PostService'
import PostList from '../components/PostList.vue'

export default {
    components: {
    PostList,
    SideBar,
    theHeader,

  }, data() {
    return {
      posts: [],
      denName: this.$route.params.denName,
    
    }
  },

  methods: {

    getPosts() {
      PostService.getPosts(this.denName).then(response => {
        this.posts = response.data;
      }).catch(error => {
        console.log('You are out of luck')
      })
    }
  },
  created() {
    this.getPosts();
  }

}
</script>

<style>
div {
  text-align: center;

}

</style>