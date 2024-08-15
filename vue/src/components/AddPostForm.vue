<template>
  <div class="form-container">
    <button class="toggle-button" v-on:click="toggleForm" v-show="!formOpen">Add Post:</button>
    <form v-on:submit.prevent="makeNewPost" v-show="formOpen" class="post-form">
      <div class="field">
        
        <div class="trashCanAndPost">
          <label for="postTitle" class="titleLabel">Post Title:</label>
      <i class="fa-solid fa-trash trashCan" id="trashCanIcon" v-on:click="toggleForm()"> </i>
    </div>
        
        <input
          type="text"
          id="postTitle"
          name="postTitle"
          v-model="newPost.postTitle"
          required
        />
      </div>
      <div class="field">
        <label for="postDesc">Post Description:</label>
        <textarea
          id="postDesc"
          name="postDesc"
          v-model="newPost.postDesc"
          required
        ></textarea>
      </div>
      <button type="submit" class="submit-button">Post</button>
      
    </form>
  </div>
</template>

<script>
import PostService from "../services/PostService.js";

export default {
  props: {
    dens: {
      type: Object,
    },
  },

  data() {
    return {
      formOpen: false,
      newPost: {
        creatorId: this.$store.state.user.id,
        creatorUsername: this.$store.state.user.username,
        postTitle: "",
        postDesc: "",
        denName: this.$route.params.denName,
        isPinned: false
      },
    };
  },

  methods: {
    toggleForm() {
      this.formOpen = !this.formOpen;
      if (!this.formOpen) this.clearForm();
    },
    
    makeNewPost() {
      PostService.makeNewPost(this.newPost)
        .then((response) => {
          console.log(response.data);
          this.getPosts(this.$route.params.denName);
          this.toggleForm();
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });
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

    clearForm() {
      this.newPost = {
        creatorId: this.$store.state.user.id,
        creatorUsername: this.$store.state.user.username,
        postTitle: "",
        postDesc: "",
        denName: this.$route.params.denName,
        isPinned: false
      };
    },
  },
};
</script>

<style scoped>
/* Container for the form and button */
.form-container {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* Style for the toggle button */
.toggle-button {
  background-color: transparent; /* Transparent background */
  color: #6c757d; /* Grey text color */
  border: 2px solid #6c757d; /* Grey border */
  padding: 5px 10px; /* Smaller padding */
  border-radius: 5px;
  font-size: 14px; /* Smaller font size */
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s; /* Transition effects */
}

.toggle-button:hover {
  background-color: #6c757d; /* Grey background on hover */
  color: white; /* White text color on hover */
}

/* Style for the form */
.post-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 20px;
}

/* Style for each field */
.field {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.field label {
  font-size: 14px;
  font-weight: bold;
}

.field input,
.field textarea {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

.field textarea {
  resize: vertical;
  min-height: 100px;
}

/* Style for the submit button */
.submit-button {
  background-color: #619b8a;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  
}
.trashCanAndPost{
  display: inline-flex;
  justify-content: center;
  width: 100%;
}
.titleLabel{
  width:99%;
}

.fa-trash{
  display: flex;
  flex-flow: row;
  align-items: center;
  align-content: center;
  
}
.submit-button:hover {
  background-color: #218838;
}
</style>
