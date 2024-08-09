<template>
  <div>
    <button v-on:click = "(commentOPen = !commentOPen);">Comments:</button>

    <div v-show="commentOPen == true">

    <div 
      class="comment"
      v-for="comment in this.comments"
      v-bind:key="comment.postId"
    
    > 
    <p>{{getVotesInfo(comment)}}</p>

      <p> {{comment.creatorName}}: {{ comment.responseDesc }} </p>

      <button v-if="comment.creatorId == $store.state.user.id"  v-on:click="deleteComment(comment)">delete comment</button>
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
</div>
</template>

<script>
import PostService from "../services/PostService";
import VotingService from "../services/VotingService";


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
      commentOPen: false,

      newComment: {
        responseId: 0,
        responseDesc: "",
        postId: this.post.postId,
        creatorId: this.$store.state.user.id,
        creatorName: this.$store.state.user.username,
        denName: this.$route.params.denName,
      },
      
    };
  },
  computed: {



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
          console.log(error)
        })

      }
  
    },
    clearForm(){
      this.newComment = {
        responseId: 0,
        responseDesc: "",
        postId: this.post.postId,
        creatorId: this.$store.state.user.id,
        creatorName: this.$store.state.user.username,
        denName: this.$route.params.denName,
      }
    },

    getVotesInfo(comment){
      console.log("votes")
      VotingService.retrieveVoteInformationForComments(comment).then(response => {
        console.log("retrieved")
        console.log(response.data);
      }).catch(error => {
        console.log(error)
      })

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
  width: 20%;
justify-content: center;
  background-color: #c6bebe;
  cursor: pointer;
}
</style>