<template>
    <div>
        <theHeader id="header" />
        <add-post-form id="addPostForm"/>
        <PostList v-bind:posts="posts" />
           
        
    </div>
    

</template>

<script>

import theHeader from '../components/Header.vue'
import PostService from '../services/PostService'
import PostList from '../components/PostList.vue'
import AddPostForm from '../components/AddPostForm.vue'


export default {
    components: {
    PostList,
    theHeader,
    AddPostForm,
  

  }, data() {
    return {
      posts: [],
      denName: this.$route.params.denName,
    
    }
  },

  methods: {

    getPosts(name) {
      PostService.getPosts(name).then(response => {
        this.posts = response.data;
      }).catch(error => {
        console.log('You are out of luck')
      })
    }
  },
  created() {
    this.getPosts(this.$route.params.denName);
  }

}
</script>

<style>
div {
  text-align: center;

}

</style>