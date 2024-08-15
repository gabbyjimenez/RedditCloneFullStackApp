<template>
  <div>
    <!-- referencing DenName that is pulled from URL -->
    <theHeader id="header" />

    <!-- DEN NAME TITLE -->
    <h1>{{ denName }}</h1>


    <AddPostForm id="addPostForm" />
    <PostList/>

     <!-- THIS IS THE 'Back to Dens' BUTTON  -->
    <div class="button-container">
      <router-link v-bind:to="{ name: 'dens' }">
        <button id="backToDensListButton" type="button" class="btn btn-primary">
          Back to Dens List
        </button>
      </router-link>
    </div>
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
#addPostForm {
padding: 1%;

}
/* THIS MAKE THE 'Back to Dens' button go to the center */
.button-container {
  display: flex;
  justify-content: center; /* Center horizontally */
  margin-top: 1em; /* Optional: Add space above the button */
}

/* COLOR OF Back To Dens List button */
#backToDensListButton {
  background-color: #619c8a;
  border-color: #619c8a;
  color: white;
}

</style>