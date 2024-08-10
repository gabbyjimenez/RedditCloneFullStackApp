<template>
  <div>
    <button id="commentsButton" v-on:click="commentOPen = !commentOPen">
      Comments:
    </button>
    <div class="comment-bottom bg-white p-2 px-4">
      <div class="d-flex flex-row add-comment-section mt-4 mb-4">
        <!-- Your existing content -->
      </div>

      <div v-show="commentOPen">
        <form v-on:submit.prevent="addComment(newComment)">
          <input
            id="newComment"
            name="newComment"
            v-model="newComment.responseDesc"
            type="text"
            class="form-control mr-3"
            placeholder="Add comment"
          />
          <button type="submit" class="btn btn-primary">Comment</button>
        </form>

        <div
          id="commentContainer"
          class="comment"
          v-for="comment in comments"
          :key="comment.postId"
        >

        <!-- comment userName and Timestamp -->
          <div class="commented-section mt-2">
            <div class="d-flex flex-row align-items-center commented-user">
              <h5 class="mr-2">{{ comment.creatorName }}</h5>
              <span class="dot mb-1"></span>
              <span class="mb-1 ml-2">4 hours ago</span>
            </div>
            <div class="comment-text-sm">
              <span>{{ comment.responseDesc }}</span>
            </div>
            <div class="reply-section">
              <div class="d-flex flex-row align-items-center voting-icons">
                <button
                  class="arrow-button upvote"
                  v-on:click.prevent="upVote(comment)"
                >
                  <img class="upvoteIcon" src="https://res.cloudinary.com/drtlz85pc/image/upload/v1723313591/icons8-scroll-up-48_wdtyrj.png">
                </button>
                <span class="ml-2">{{ comment.upvotes - comment.downvotes }}</span>
                
                <h6 class="ml-2 mt-1"></h6>
                <button
                  class="arrow-button downvote"
                  v-on:click.prevent="downVote(comment)"
                >
                <img class="downvoteIcon" src="https://res.cloudinary.com/drtlz85pc/image/upload/v1723313580/icons8-scroll-down-48_kj3aqm.png">
                </button>
                
              </div>
              <div id="commentButtons">
                <button
                  v-if="comment.creatorId == $store.state.user.id"
                  v-on:click="deleteComment(comment)"
                >
                  Delete
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import PostService from "../services/PostService";
import VotingService from "../services/VotingService";

export default {
  props: {
    post: {
      type: Object,
    },
  },

  data() {
    return {
      searchFilter: "",
      comments: [],
      votes: [],
      commentOPen: false,

      newComment: {
        responseId: 0,
        responseDesc: "",
        postId: this.post.postId,
        creatorId: this.$store.state.user.id,
        creatorName: this.$store.state.user.username,
        denName: this.$route.params.denName,
      },
    };
  },
  computed: {},
  methods: {
    getComments(post) {
      PostService.getComments(post)
        .then((response) => {
          this.comments = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addComment(newComment) {
      console.log(this.post.postId);
      console.log("post id");
      PostService.addComment(newComment)
        .then((response) => {
          this.getComments(this.post);
          console.log(response.data);
          console.log(this.newComment);
          this.clearForm();
        })
        .catch((error) => {
          console.log(error);
        });
    },

    deleteComment(comment) {
      console.log("bleep");
      if (
        confirm(
          "Are you sure you want to delete this message? This action cannot be undone."
        )
      ) {
        console.log("blorg");

        PostService.deleteComment(this.$route.params.denName, comment)
          .then((response) => {
            this.getComments(this.post);
            console.log("deleted");
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    clearForm() {
      this.newComment = {
        responseId: 0,
        responseDesc: "",
        postId: this.post.postId,
        creatorId: this.$store.state.user.id,
        creatorName: this.$store.state.user.username,
        denName: this.$route.params.denName,
      };
    },

    getVotesInfo(comment) {
      VotingService.retrieveVoteInformationForComments(comment)
        .then((response) => {
          this.getComments(this.post);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    upVote(comment) {
      VotingService.upvoteCommentForResponse(comment)
        .then((response) => {
          console.log("upvote");
          console.log(response.data);
          this.getComments(this.post);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    downVote(comment) {
      VotingService.downvoteCommentForResponse(comment)
        .then((response) => {
          console.log(response.data);
          this.getComments(this.post);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  created() {
    this.getComments(this.post);
  },
};
</script>

<style scoped>
/* #commentContainer {
  display: flex;
  height: 5%;
  width: 100%;
  margin: 1%;
  align-items: center;
  justify-content: space-between;
  background-color: #9696966c;
}
#commentUsername {
  font-weight: bold;
  align-items: baseline;
}
#commentButtons {
  display: flex;
  height: 10%;
  justify-content: space-evenly;
}
.comment {
  border-bottom: 1px solid #f2f2f2;
  display: flex;
  padding: 10px 20px;
  width: 100%;
  justify-content: center;
  background-color: #c6bebe;
  cursor: pointer;
}
#commentMessage {
  display: flex;
  flex-direction: row;
  align-items: center;
}

#selectedButton {
  background-color: aqua;
}
#commentsButton {
  display: flex;
  width: 8%;
  margin: auto;
  margin-top: 0.1%;
  margin-bottom: 0.1%;
  justify-content: center;
} */

/* Template Nobody Panic */

/* body {
  background-color: #eee;
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

.dot {
  height: 7px;
  width: 7px;
  margin-top: 3px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}

.hit-voting:hover {
  color: blue;
}

.hit-voting {
  cursor: pointer;
} */




/* General styles */
body {
  background-color: #eee;
  display: flex;
}

#commentContainer {
  display: flex;
  height: 5%;
  width: 80%;
  margin: 1%;
  align-items: center;
  justify-content: space-between;
  background-color: #fff8f86c;
  border:2px solid black;

}

.commented-section{
  padding-left: 2%;
  display: flex;
  justify-content: flex-start;
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



.arrow-button {
  background: none;
  border: none;
  font-size: 28px;
  cursor: pointer;
  color: #007bff; /* Adjust color as needed */
  padding: 5px;
}

.arrow-button:hover {
  color: #0056b3; /* Adjust hover color as needed */
}

/* Other existing styles */
.comment-bottom {
  /* Your existing styles */
}
.comments {
  text-decoration: underline;
  text-underline-position: under;
  cursor: pointer;
}

.dot {
  height: 7px;
  width: 7px;
  margin-top: 3px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}

.hit-voting:hover {
  color: blue;
}

.hit-voting {
  cursor: pointer;
}

.upvoteIcon, .downvoteIcon{
  width:1rem;
  height:auto;
  
}

.upvoteIcon:hover{
  filter: invert(77%) sepia(15%) saturate(756%) hue-rotate(47deg) brightness(94%) contrast(87%);
}

.downvoteIcon:hover{
  filter: invert(62%) sepia(46%) saturate(3893%) hue-rotate(341deg) brightness(105%) contrast(99%);
}

.align-items-center{
  display:flex;
  justify-content: center;
  align-content: center;
  text-align: center;
}

/* Your other existing styles */
</style>

