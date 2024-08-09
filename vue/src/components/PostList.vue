<template>
  <div id="searchPost">
    <label for="">Search: </label>
    <input type="text" name="denName" v-model="searchFilter" />
  </div>

  <div class="den" v-for="post in filteredPosts" v-bind:key="post.postTitle">
    <div id="postHeader" v-bind="post">
      <label id="postTitle">{{ post.postTitle }} : {{ post.creatorUsername }}</label>
      <p id="postDescription">Desc</p>
      <p>{{ post.postDesc }}</p>
      <button id="deleteButton" v-if="post.creatorUsername == $store.state.user.username" v-on:click="deletePost(post)">delete</button>
      <comments-list id="" v-bind:post="post" />
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
      return this.$store.state.posts.filter((post) => {
        return this.searchFilter == ""
          ? true
          : post.postTitle.includes(this.searchFilter);
      });
    },
  },

  methods: {
    deletePost(post) {
      console.log("bleep");
      if (
        confirm(
          "Are you sure you want to delete this message? This action cannot be undone."
        )
      ) {
        console.log(post);
        console.log("blorg");

        PostService.deletePost(post)
          .then((response) => {
            this.getPosts(this.$route.params.denName)
            console.log("deleted");
          })
          .catch((error) => {
            this.handleErrorResponse(error, "deleting");
          });
      }
    },
    getPosts(name) {
      PostService.getPosts(name)
        .then((response) => {
          this.$store.state.posts = response.data;
        })
        .catch((error) => {
          console.log("You are out of luck");
        });
    },
  },
  created(){
    this.getPosts(this.$route.params.denName)
  }

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