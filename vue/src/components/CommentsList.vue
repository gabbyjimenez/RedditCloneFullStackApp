<template>
  <div>
    <div
      class="comment"
      v-for="comment in this.comments"
      v-bind:key="comment.postId"
    >
      <p>{{ comment.responseDesc }} {{ comment.creatorId }}</p>
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
    posts: {
      type: Array,
    },
    comments:{
        type: Array,
    }
  },

  data() {
    return {
      searchFilter: "",
      newComment: {
        responseDesc: "",
        postId: 0,
        creatorId: 1,
        denName: this.$route.params.denName,
      },
      comments: [],
    };
  },
  computed: {
    filteredComments() {
      return this.comments.filter((comment) => {
        return this.searchFilter == ""
          ? true
          : comment.responseDesc.includes(this.searchFilter);
      });
    },
  },
  
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