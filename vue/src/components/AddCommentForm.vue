<template>
    <div>
        <form v-on:submit.prevent="makeNewComment">
     
      <div class="field">
        <label for="responseDesc">Comment</label>
        <textarea
          id="responseDesc"
          name="responseDesc"
          v-model="newComment.responseDesc"
          required
        >
        </textarea>
      </div>
      <button type="submit">Comment</button>
    </form>
    </div>
</template>

<script>

import PostService from '../services/PostService.js';

export default {


data() {

    return {

        newComment: {
            responseDesc: '', 
            postId: 0,
            creatorId: 1,
            denName: ""
    }


    }

},

methods: {

    makeNewComment() {
      PostService.makeNewComment(this.newComment)
        .then((response) => {
          console.log(response.data);
          console.log(this.newComment);
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    

    clearForm() {
      return (this.newComment = {
        responseDesc: '', 
            postId: 0,
            creatorId: 1,
            denName: ""
      });
    }

}

}

</script>

<style scoped>

</style>