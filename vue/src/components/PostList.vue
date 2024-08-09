<template>
  <div id="searchPost">
    <label for="">Search: </label>
    <input type="text" name="denName" v-model="searchFilter" />
  </div>

  <div class="den" v-for="post in filteredPosts" v-bind:key="post.postTitle">
    <div id="postBody" v-bind="post">
      <label for="postLabel"><p id="postTitle">{{ post.postTitle }}</p><p id="postUser">{{ post.creatorUsername }}</p></label>
      <p id="postDescription">Description: </p>
      <p>{{ post.postDesc }}</p>
      <button v-if="post.creatorUsername == $store.state.user.username" v-on:click="deletePost(post)">Delete</button>
      <comments-list id="" v-bind:post="post" />
    </div>
    <button id="upvote">Upvote</button>
    <button id="downvote">Downvote</button>
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
  border-bottom: 1px solid #f2f2f200;
  display: block;
  padding: 10px 20px;
  background-color: #f2f2f2;
  cursor: pointer;
}
#postBody{
  flex-direction: column;
  display: flex;
  font-size: larger;
}
label[for=postLabel]{
display: flex;
flex-direction: row;
border:1px solid rgba(0, 0, 0, 0);
background-color: rgba(217, 217, 217, 0.76);

}
#postTitle {
display: flex;
width: 100%;
justify-content: flex-start;
font-size: x-large;

}
#postUser { 
display: flex;
justify-content: flex-end;
width: 100%;
}
p {
 display: flex;
 justify-content: flex-start;
 width: 100%;
 padding: 1%;
 font-size: larger;
}
#postDescription {
  display: flex;
 justify-content: flex-start;
 width: 100%;
 padding: 0%;
 font-size: small;

}

#searchPost {
  display: flex;
  margin: auto;
  justify-content: center;
}
button {
display: flex;
width: 6%;
margin: auto;
margin-top: 0.1%;
margin-bottom: 0.1%;
justify-content: center;

  
}

</style>