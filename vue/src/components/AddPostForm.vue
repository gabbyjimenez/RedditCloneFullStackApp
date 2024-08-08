<template>
  <div>
    <button v-on:click = "(formOpen = !formOpen); clearForm();">Add Post:</button>
    <form v-on:submit.prevent="makeNewPost" v-show="formOpen == true" >
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
      <button type="submit" >Post</button>
    </form>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";
import DenView from "../views/DenView.vue";
export default {
  props: {
    dens: {
      type: Object,
    },
  },

  data() {
    return {

    formOpen: false,

    newPost: {
        creatorId: this.$store.state.user.id,
        creatorUsername: this.$store.state.user.username,
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
          this.getPosts(this.$route.params.denName);
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });

    },
    getPosts(name) {
      console.log("hit")
      PostService.getPosts(name)
        .then((response) => {
          this.$store.state.posts = response.data;
     
        })
        .catch((error) => {
          console.log("You are out of luck");
        });
    },
    

    clearForm() {
      return (this.newPost = {
        creatorId: this.$store.state.user.id,
        creatorUsername: this.$store.state.user.username,
        postTitle: "",
        postDesc: "",
        denName: this.$route.params.denName,
      });
    }
        

    },

   
  };

  



</script>

<style>
</style>