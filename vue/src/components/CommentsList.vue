<template>
  <div>
    <!-- Displaying comments. Did not touch anything below this line-->
    <p>Comments: </p>
    <div class="comment" v-bind:id="post.postId">
        <div
          class="comment"
          v-for="comment in comments"
          v-bind:key="comment.postId"
        >
          <p> {{comment.responseDesc}} </p>
        </div>
      </div>
    <form v-on:submit.prevent="addComment()">
      <label for="newComment">Add Comment: </label>
      <textarea
        id="newComment"
        name="newComment"
        v-model="newComment.responseDesc"
      ></textarea>
    </form>
    <button type="submit">Comment</button>
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
      comments:[],

      newComment: {
        responseDesc: "",
        postId: 0,
        creatorId: 1,
        denName: this.$route.params.denName,
      },
      
    };
  },
  computed: {
    //Doesn't Do Anything. From Copy Paste

    filteredComments() {
      return this.comments.filter((comment) => {
        return this.searchFilter == ""
          ? true
          : comment.responseDesc.includes(this.searchFilter);
      });
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