<template>
  <div id="searchPost">
    <label for="searchFilter">Search: </label>
    <input type="text" id="searchFilter" name="denName" v-model="searchFilter" />
  </div>

  <div class="den" v-for="post in filteredPosts" v-bind:key="post.postTitle">
    <div id="postBody" v-bind="post">
      <!-- TEMPLATE START -->

      <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
          <div class="d-flex flex-column col-md-8">
            <div class="d-flex flex-row align-items-center text-left comment-top p-2 bg-white border-bottom px-4">
              <div class="profile-image">
                <img class="rounded-circle" src="https://i.imgur.com/t9toMAQ.jpg" width="70" />
              </div>
              <div class="d-flex flex-column-reverse flex-grow-0 align-items-center votings ml-1">
                <i class="fa fa-sort-up fa-2x hit-voting"></i><span>{{ post.upvotes - post.downvotes }}</span>
                <i class="fa fa-sort-down fa-2x hit-voting"></i>
              </div>
              <div class="d-flex flex-column ml-3">
                <div class="d-flex flex-row post-title">
                  <h5>{{ post.postTitle }}</h5>
                  <span class="ml-2">{{ post.creatorUsername }}</span>
                </div>
                <div class="d-flex flex-row align-items-center align-content-center post-title">
                  <span class="bdge mr-1">Question</span>
                  <span class="mr-2 dot"></span><span> Timestamp </span>
                </div>
                 <div>
              <h6 id="postDesc">{{ post.postDesc }}</h6>
            </div>
                <div class="button-container">
                  <button class="image-button upvote" v-on:click.prevent="upVote(post)">
                    <img class="upvoteIcon" src="https://res.cloudinary.com/drtlz85pc/image/upload/v1723313591/icons8-scroll-up-48_wdtyrj.png" alt="Upvote" />
                  </button>
                  <span class="ml-2">{{ post.upvotes - post.downvotes }}</span>
                  <button class="image-button downvote" v-on:click.prevent="downVote(post)">
                    <img class="downvoteIcon" src="https://res.cloudinary.com/drtlz85pc/image/upload/v1723313580/icons8-scroll-down-48_kj3aqm.png" alt="Downvote" />
                  </button>
                </div>
                <button v-if="post.creatorUsername == $store.state.user.username" v-on:click="deletePost(post)">Delete</button>
              </div> 
              <span class="mr-2 comments">Comment Number &nbsp;</span>
            </div>
           
          </div>
        </div>
      </div>

      <!-- TEMPLATE END -->
      <comments-list v-bind:post="post"/>
    </div>
  </div>
</template>
<script>
import PostService from "../services/PostService";
import CommentsList from "../components/CommentsList.vue";
import VotingService from "../services/VotingService.js";
export default {
  components: {
    CommentsList,
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
    
    getVotesInfo(post){
      VotingService.retrieveVoteInformationForPosts(post).then(response => {
      }).catch(error => {
        console.log(error)
      })

    },
    upVote(post){
      VotingService.makeUpvoteForPost(post).then(response => {
        this.getPosts(this.$route.params.denName)
        console.log('upvote')
        console.log(response.data);
      }).catch(error => {
        console.log(error)
      })
    },
    downVote(post){
      VotingService.makeDownvoteForPost(post).then(response => {
        this.getPosts(this.$route.params.denName)
        console.log(response.data);
      }).catch(error => {
        console.log(error)
      })
    },

  },
  created(){
    this.getPosts(this.$route.params.denName)
  }

};


</script>
<style scoped>
/* Remove default button styling */
.image-button {
  background: none; /* Remove default button background */
  border: none; /* Remove default button border */
  padding: 0; /* Remove default button padding */
  margin: 0; /* Remove default button margin */
  cursor: pointer; /* Change cursor to pointer */
  display: inline-flex; /* Make sure buttons are inline with other content */
}

/* Ensure images inside buttons fit well */
.image-button img {
  display: block; /* Remove extra space below image */
  width: 1rem; /* Adjust width as needed */
  height: auto; /* Maintain aspect ratio */
}

/* Additional styles for button container */
.button-container {
  display: flex;
  flex-flow: row;
  align-items: center; /* Center items vertically */
  justify-content: center; /* Center items horizontally */
}

.upvoteIcon:hover {
  filter: invert(77%) sepia(15%) saturate(756%) hue-rotate(47deg) brightness(94%) contrast(87%);
}

.downvoteIcon:hover {
  filter: invert(62%) sepia(46%) saturate(3893%) hue-rotate(341deg) brightness(105%) contrast(99%);
}

.dot {
  height: 7px;
  width: 7px;
  margin-top: 3px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}

.hit-voting {
  cursor: pointer;
}

.hit-voting:hover {
  color: blue;
}

#postDesc {
  margin: 5%;
  justify-content: flex-start;
}

body {
  background-color: #eee;
  display: flex;
}

.bdge {
  height: 21px;
  background-color: orange;
  color: #fff;
  font-size: 11px;
  padding: 8px;
  border-radius: 4px;
  line-height: 3px;
}

.comments {
  text-decoration: underline;
  text-underline-position: under;
  cursor: pointer;
}
</style>
