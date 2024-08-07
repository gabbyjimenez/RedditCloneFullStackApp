<template>


    <div>
        <label for="">Search: </label>
        <input type="text" name="denName" v-model="searchFilter">
    </div>

    <div class="den" v-for="post in filteredPosts" v-bind:key="post.postTitle" >
        <div v-on:click="$router.push({ name: 'post', params: { postId: post.postId } })" v-bind="post">
            <label>{{ post.postTitle }} : {{ post.creatorUsername }}</label>
            <p>Desc</p>
            <p>{{ post.postDesc }}</p>
            <button v-on:click="DeletePost(post)">delete</button>
            <CommentsList />


        </div>
    </div>
 
</template>

<script>
import PostService from "../services/PostService";
import CommentsList from "../components/CommentsList.vue";
export default {
  components: {
    CommentsList,
  },
  props: {
    posts: {
      type: Array,
    },
  },

  data() {
    return {
      searchFilter: "",
      
    };
  },
  computed: {
    filteredPosts() {
      return this.posts.filter((post) => {
        return this.searchFilter == ""
          ? true
          : post.postTitle.includes(this.searchFilter);
      });
    },
  },
  methods: {
    DeletePost(post) {
      console.log("bleep");
      if (
        confirm(
          "Are you sure you want to delete this message? This action cannot be undone."
        )
      ) {
        // TODO - Do a delete, then navigate to Topic Details on success
        // For errors, call handleErrorResponse
        console.log(post);
        console.log("blorg");

        PostService.deletePost(post)
          .then((response) => {
            console.log("deleted");
          })
          .catch((error) => {
            this.handleErrorResponse(error, "deleting");
          });
      }
    },
    
  },
  
};
</script>

<style scoped>
.den {
  border-bottom: 1px solid #f2f2f2;
  display: block;
  padding: 10px 20px;
  background-color: #f2f2f2;
  cursor: pointer;
}
</style>