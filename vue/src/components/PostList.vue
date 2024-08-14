<template>
  <div id="searchPost" class="search-post">
  
    <input type="text" id="searchFilter" name="denName" v-model="searchFilter"
      placeholder="Search by Title/Post Content" />
  </div>

  <div class="den" v-for="post in filteredPosts" v-bind:key="post.postTitle">
    <div id="postBody" v-bind="post">
      <div class="d-flex justify-content-center row">
        <div class="d-flex flex-column col-md-8 second-container border-bottom">
          <div class="d-flex flex-row align-items-center text-left comment-top p-2 bg-white px-4">
            <div class="profile-image">
              <img class="rounded-circle"
              v-bind:src="post.pfpUrl" width="70">
            </div>
            <div class="d-flex flex-column-reverse flex-grow-0 align-items-center votings ml-1">
              <i class="fa fa-sort-up fa-2x hit-voting upvoteIcon" v-on:click.prevent="upVote(post)"></i><span>{{
                post.upvotes - post.downvotes }} </span>
              <i class="fa fa-sort-down fa-2x hit-voting downvoteIcon" v-on:click.prevent="downVote(post)"></i>
            </div>

            <div class="d-flex flex-column ml-3 titleDiv">
              <div class="d-flex flex-row post-title">
                <h5>{{ post.postTitle }}</h5>
                <i v-if="post.creatorUsername == $store.state.user.username" v-on:click="deletePost(post)"
                  class="fa-solid fa-trash trashCan" id="trashCanIcon">
                </i>

                <i v-if="getThisDen(post)" v-on:click="pinToggle(post)" class="fa-solid fa-flag" :class="{ 'flagged': post.pinned }"></i> 
                <i v-else-if="post.pinned == true" class="fa-solid fa-flag pinflag" :class="{ 'flagged': post.pinned }"></i>   
  
              </div>

              <div>
                <span class="ml-2 username">@{{ post.creatorUsername }} </span>
              </div>

              <div class="d-flex flex-row align-items-center align-content-center post-title">
                <span class="postTime"> {{ formatLocalDateTimeWithAMPM(post.timeCreated) }} </span>
              </div>
            </div>
          </div>
          <div class="delete" id="postDesc">
            <h6>{{ post.postDesc }}</h6>
          </div>
          <comments-list v-bind:post="post" class="comments-list" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import PostService from "../services/PostService";
import CommentsList from "../components/CommentsList.vue";
import VotingService from "../services/VotingService.js";
import PinningService from "../services/PinningService.js";
import DenService from "../services/DenService.js";
export default {
  components: {
    CommentsList,
  },

  data() {
    return {
      searchFilter: "",
      pfpCache: {}
    };
  },
  computed: {
    filteredPosts() {
      const searchFilter = this.searchFilter.toLowerCase();

      // Filter posts based on searchFilter
      const filtered = this.$store.state.posts.filter((post) => {
        const nameMatch = post.postTitle.toLowerCase().includes(searchFilter);
        const contentMatch = post.postDesc.toLowerCase().includes(searchFilter);

        // Return true if either condition matches or searchFilter is empty
        return searchFilter === "" ? true : nameMatch || contentMatch;
      });

      // Sort posts: pinned posts first, then the rest
      return filtered.sort((a, b) => {
        if (a.isPinned === b.isPinned) {
          return 0; // No change in order if both posts have the same isPinned value
        }
        return a.isPinned ? -1 : 1; // Pinned posts (-1) come before unpinned posts (1)
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
            this.getPosts(this.$route.params.denName);
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
    getThisDen(post) {
      return this.$store.state.dens.find(den =>
        post.denId == den.denId &&  den.denCreatorId === this.$store.state.user.id 
      );
    },


  getVotesInfo(post) {
    VotingService.retrieveVoteInformationForPosts(post)
      .then((response) => { })
      .catch((error) => {
        console.log(error);
      });
  },
  upVote(post) {
    console.log(this.$store.state.user.userId)
    if (this.$store.state.user.userId != 0) {
      VotingService.makeUpvoteForPost(post)
        .then((response) => {
          this.getPosts(this.$route.params.denName);
          console.log("upvote");
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      this.$router.push("/login");
    }
  },
  downVote(post) {
    console.log(this.$store.state.user.userId)
    if (this.$store.state.user.userId != 0) {
      VotingService.makeDownvoteForPost(post)
        .then((response) => {
          this.getPosts(this.$route.params.denName);
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      this.$router.push("/login");
    }
  },
  pinToggle(post) {

    PinningService.pinToggle(post).then((response) => {
      this.getPosts(this.$route.params.denName);
      console.log(response.data);
    });
  },

  formatLocalDateTimeWithAMPM(localDateTime) {
    if (
      !localDateTime ||
      !localDateTime.match(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}$/)
    ) {
      throw new Error("Invalid LocalDateTime format");
    }

    const date = new Date(localDateTime.replace("T", " "));

    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const year = date.getFullYear();

    let hours = date.getHours();
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");

    const ampm = hours >= 12 ? "PM" : "AM";

    hours = hours % 12;
    hours = hours ? hours : 12;

    const formattedHours = String(hours).padStart(2, "0");

    return `${month}/${day}/${year} ${formattedHours}:${minutes} ${ampm}`;
  },
  getPfpLink(userName){

    let pfpLink = ''

      PostService.getPfpLink(userName).then((response) => {
          console.log(response.data);
          pfpLink = response.data.toString();
          console.log('pfpLink: ', pfpLink)
          
        })
        .catch((error) => {
          console.log(error);
        });
        console.log(pfpLink)
        return pfpLink;
  }
},
created() {
  this.getPosts(this.$route.params.denName);

  DenService.getDens().then(response => {
    this.$store.state.dens = response.data;
  }).catch(error => {
    console.log('You are out of luck');
  });
},
};
</script>
<style scoped>
/* Remove default button styling */

#postBody, .search-post{
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
#searchPost{
  display: inline-flex;
  width: 75%;
  justify-content: center;
}
#searchFilter{
  display: inline-flex;
  text-align: center;
  width:50%;

}
.image-button {
  background: none;
  /* Remove default button background */
  border: none;
  /* Remove default button border */
  padding: 0;
  /* Remove default button padding */
  margin: 0;
  /* Remove default button margin */
  cursor: pointer;
  /* Change cursor to pointer */
  display: inline-flex;
  /* Make sure buttons are inline with other content */
}

/* Ensure images inside buttons fit well */
.image-button img {
  display: block;
  /* Remove extra space below image */
  width: 1rem;
  /* Adjust width as needed */
  height: auto;
  /* Maintain aspect ratio */
}

/* Additional styles for button container */
.button-container {
  display: flex;
  flex-flow: row;
  align-items: center;
  /* Center items vertically */
  justify-content: center;
  /* Center items horizontally */
}

.upvoteIcon:hover {
  color: #a1c181;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

.downvoteIcon:hover {
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

.fa-flag{

  align-content: center;
}

.fa-trash:hover {
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

.fa-flag:hover{
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

/* DOT */
.dot {
  height: 0.5rem;
  width: 0.5rem;
  margin: 0.5rem;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}

.hit-voting {
  cursor: pointer;
}

/* .hit-voting:hover {
  color: blue;
} */

.votings {
  padding: 0.5rem;
}

.username {
  display: flex;
  text-align: bottom;
  padding-left: 0.5rem;
  vertical-align: bottom;
}

img {
  justify-content: flex-start;
}

body {
  background-color: #eee;
  display: flex;
  max-width: 90%;
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

.delete {
  width: 80%;
  margin-left: 59px;
  display: inline-block;
  word-wrap: break-word;
  justify-content: center;
  text-align: justify;
}

.comments {
  text-decoration: underline;
  text-underline-position: under;
  cursor: pointer;
}

.comments-list {
  display: flex;
  flex-flow: column;
  justify-content: flex-start;
  align-items: center;
}

.second-container {
  border-top: solid #619b8a 1px;
}

#searchPost {
  padding-bottom: 1rem;
}

h5 {
  margin-bottom: 0%;
  margin-top: 0%;
}

h6 {
  display: inline-block;
  width: 100%;
  /* text-wrap: break-word; */
}

#trashCanIcon {
  display: flex;
  justify-content: flex-start;
  padding-left: 10px;
  align-content: end;
}

#trashCanIcon {
  display: inline-block;
  align-content: center;
  justify-content: center;
}

.fa-flag{
  padding-left: 10px;
}

.flagged {
  color: #fe532d;
}

.titleDiv {
  padding-left: .5rem;
}

.postTime {
  font-size: 90%;
}
</style>
