<template>
  <div>
    <h4>Add Post:</h4>
    <form v-on:submit="">
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
    </form>
  </div>
</template>

<script>
import PostService from "../services/PostService";
export default {
  props: {
    dens: {
      type: Object,
    },
  },

  data() {
    return {
      newPost: {
        creatorId: this.$store.state.user,
        denId: this.den.denId,
        postTitle: "",
        postDesc: "",
        denName: this.den.denName,
      },
    };
  },

  methods: {
    
    
    setNewPostId(denName){

        const den = this.dens.filter(denName => {

            


        })





    },
    makeNewPost(newPost) {
      return PostService.makeNewPost(newPost)
        .then((response) => {
          console.log(response.data);
          console.log(newPost);
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });
    },

    clearForm() {
      return (this.newPost = {});
    },
  },

  created:{





  }


};
</script>

<style>
</style>