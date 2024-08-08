<template>
  <div>
    <theHeader id="header" />
    <AddPostForm id="addPostForm" />
    <PostList/>
  </div>
</template>

<script>
import theHeader from "../components/Header.vue";
import PostService from "../services/PostService";
import PostList from "../components/PostList.vue";
import AddPostForm from "../components/AddPostForm.vue";

export default {
  components: {
    PostList,
    theHeader,
    AddPostForm,
  },
  data() {
    return {
      comments: [],
      denName: this.$route.params.denName,
    };
  },

  methods: {

    getPosts(name) {
      PostService.getPosts(name)
        .then((response) => {
          this.$store.state.posts = response.data;
    
        })
        .catch((error) => {
          console.log("You are out of luck");
        });
    },
  

    getComments(post) {
      PostService.getComments(post)
        .then((response) => {
          this.comments = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
 
};
</script>

<style>
div {
  text-align: center;
}
</style>