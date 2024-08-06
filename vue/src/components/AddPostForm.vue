<template>
  <div>
    <h4>Add Post:</h4>
    <form v-on:submit.prevent="makeNewPost">
      <div class="field">
        <label for="denName">Post Title:</label>
        <input
          type="text"
          id="postTitle"
          name="postTitle"
          v-model="newPost.postTitle"
          required
        />
      </div>
      <div class="field">
        <label for="postDesc">Post Description</label>
        <textarea
          id="postDesc"
          name="postDesc"
          v-model="newPost.postDesc"
          required
        >
        </textarea>
      </div>
      <button type="submit">Post</button>
    </form>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";
export default {
  props: {
    dens: {
      type: Object,
    },
  },

  data() {
    return {
    
    newPost: {
        creatorId: 1,
        postTitle: "",
        postDesc: "",
        denName: this.$route.params.denName,
      },
      
    
    };
  },

  methods: {
    
    makeNewPost() {
      PostService.makeNewPost(this.newPost)
        .then((response) => {
          console.log(response.data);
          console.log(this.newPost);
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });
    },

    clearForm() {
      return (this.newPost = {});
    }
        

    },

   
  };

  



</script>

<style>
</style>