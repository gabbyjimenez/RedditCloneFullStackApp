<template>
  <div>
    <label for="title">Comments: </label>

    <div
      class="comment"
      v-for="comment in this.comments"
      v-bind:key="comment.postId"
    >
      <p>{{ comment.responseDesc }} {{ comment.creatorId }}</p>
      <button v-on:click="deleteComment(comment)">delete comment</button>
    </div>
    <form v-on:submit.prevent="addComment(this.newComment)">
      <label for="newComment">Add Comment: </label>
      <textarea
        id="newComment"
        name="newComment"
        v-model="newComment.responseDesc"
      ></textarea>
      <button type="submit">Comment</button>

    </form>
  </div>
</template>

<script>
import PostService from "../services/PostService";


export default {
  props: {
    post: {
      type: Object,
    },
 
  },

  data() {
    return {
      searchFilter: "",
      comments: [],

      newComment: {
        responseDesc: "",
        postId: this.post.postId,
        creatorId: 1,
        denName: this.$route.params.denName,
      },
      
    };
  },
  computed: {

    freshComments(){
      return this.comments
    },


  },
  methods:{
    
    getComments(post) {
        console.log("help");
        PostService.getComments(post)
        .then((response) => {
          this.comments = response.data;
          console.log(this.comments);
        })
        .catch((error) => {
          console.log(error);
        });       
      
        
    },
    addComment(newComment){
      console.log(this.post.postId)
      console.log("post id")
      PostService.addComment(newComment)
        .then((response) => {
          this.getComments(this.post);
          console.log(response.data);
          console.log(this.newComment);
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });
    },


    deleteComment(comment){
      console.log("bleep")
      if (confirm("Are you sure you want to delete this message? This action cannot be undone.")) {

        console.log("blorg")

        PostService.deleteComment(this.$route.params.denName, comment).then(response => {
          this.getComments(this.post);
          console.log("deleted")
        }).catch(error => {
          this.handleErrorResponse(error, 'deleting');
        })

      }
  
    },
    clearForm(){
      this.newComment = {
        responseDesc: "",
        postId: this.post.postId,
        creatorId: 1,
        denName: this.$route.params.denName,
      }
    }

  },

  created(){
    this.getComments(this.post);
  }
  
};
</script>

<style scoped>
.comment {
  border-bottom: 1px solid #f2f2f2;
  display: flex;
  padding: 10px 20px;
  background-color: #f2f2f2;
  cursor: pointer;
}
</style>