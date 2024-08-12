<template>
  <div>
    <p
      class="mr-2 comments"
      id="commentsButton"
      v-on:click.prevent="commentOPen = !commentOPen"
    >
      Comments
    </p>
    <div class="comment-bottom bg-white p-2 px-4" v-show="commentOPen">
      <div class="main-container">
        <div class="d-flex flex-row add-comment-section mt-4 mb-4">
          <img
            class="img-fluid img-responsive rounded-circle mr-2"
            src="https://res.cloudinary.com/drtlz85pc/image/upload/v1723343728/Headshot_ipay6u.jpg"
            width="38"
          />

          <input
            id="newComment"
            name="newComment"
            v-model="newComment.responseDesc"
            type="text"
            class="form-control mr-3"
            placeholder="Add comment"
          />
          <button
            type="button"
            class="btn btn-primary"
            v-on:click.prevent="addComment(newComment)"
          >
            Comment
          </button>
        </div>

        <div
          id="commentContainer"
          class="comment"
          v-for="comment in comments"
          :key="comment.postId"
        >
          <!-- TEMPLATE START -->

          <div class="comment-bottom bg-white p-2 px-4">
            <div class="commented-section mt-2">
              <div class="d-flex flex-row align-items-center commented-user">
                <h5 class="mr-2">{{ comment.creatorName }}</h5>
                <span class="dot mb-1"></span
                ><span class="mb-1 ml-2">4 hours ago</span>
              </div>
              <div class="comment-text-sm">
                <span>{{ comment.responseDesc }}</span>
              </div>
              <div class="reply-section">
                <div class="d-flex flex-row align-items-center voting-icons">
                  <i
                    class="fa fa-sort-up fa-2x upvoteIcon"
                    v-on:click.prevent="upVote(comment)"
                  ></i
                  ><i
                    class="fa fa-sort-down fa-2x hit-voting downvoteIcon"
                    v-on:click.prevent="downVote(comment)"
                  ></i
                  ><span class="dot ml-2"></span><span class="ml-2">{{comment.upvotes - comment.downvotes}}</span>
                </div>
              </div>
            </div>

            <!-- TEMPLATE END -->

            <!-- OLD CODE -->
            <!-- comment userName and Timestamp -->
            <!-- <div class="commented-section mt-2">
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
          </div> -->
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
  justify-content: center;
}

.add-comment-section {
  margin-top: 0%;
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
  justify-content: center;
}

#postBody > div:nth-child(2),
#postBody > div:nth-child(2) > div,
#postBody > div:nth-child(2) > div > div.main-container {
  justify-content: center;
  align-items: center;
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

.fa-trash:hover {
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

/* .comment-bottom, .add-comment-section, .main-container, #commentContainer{
  display:flex;
  flex-flow: column;
  width:70%;
  justify-content: center;
  align-content: center;
} */
.dot {
  height: 0.5rem;
  width: 0.5rem;
  margin: 0.5rem;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  align-self: center;
}

#commentContainer > div > div {
  align-content: center;
}

#commentContainer > div {
  width: 80%;
  justify-self: center;
}
.hit-voting:hover {
  color: blue;
}

.hit-voting {
  cursor: pointer;
}
#commentContainer {
  width: 100%;
  border-top: solid blueviolet 1px;
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
  color: #a1c181;
}

.downvoteIcon:hover {
  color: #fe7f2d;
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
  margin-bottom: 8px;
  vertical-align: bottom;
}

img {
  justify-content: flex-start;
}

/* #postDesc {
  margin: .5%;
  display: inline-block;
  
  word-wrap: break-word;
  justify-content: center;

} */

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
.delete {
  width: 80%;
  margin: 0.5rem;
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

h6 {
  display: inline-block;
  width: 100%;
  /* text-wrap: wrap; */
}

/* Your other existing styles */
</style>

