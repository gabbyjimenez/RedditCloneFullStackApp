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
        <div
          class="d-flex flex-row add-comment-section mt-4 mb-4"
          id="theActualItems"
          v-if="this.$store.state.user.userId != 0"
        >
          <img
            class="img-fluid img-responsive rounded-circle mr-2"
            v-bind:src=this.$store.state.user.pfpLink
            width="38" height="38"
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

          <div class="comment-bottom bg-white p-2 px-4 main-comment-section">
            <div class="commented-section mt-2 containing-picture">
              <div class="secure-pfp">
                <div class="profile-image">
                  <img
                    class="rounded-circle"
                    v-bind:src="comment.pfpUrl"
                    width="38" height="38"
                  />
                </div>
                <div
                  class="d-flex flex-column-reverse flex-grow-0 align-items-center votings ml-1 arrows"
                >
                  <i
                    class="fa fa-sort-up fa-2x hit-voting upvoteIcon"
                    v-on:click.prevent="upVote(comment)"
                  ></i>
                  <i
                    class="fa fa-sort-down fa-2x hit-voting downvoteIcon"
                    v-on:click.prevent="downVote(comment)"
                  ></i>
                </div>
                <span class="total-votes"
                  >{{ comment.upvotes - comment.downvotes }}
                </span>
              </div>

              <div class="d-flex user-and-time">
                <h6 class="commentCreatorName">@{{ comment.creatorName }}</h6>
                <div class="userAndTrashContainer"> <span class="commentTime">{{
                  formatLocalDateTimeWithAMPM(comment.timeCreated)
                }}</span><i v-if="comment.creatorName == $store.state.user.username" v-on:click="deleteComment(comment)"
                  class="fa-solid fa-trash trashCan" id="trashCanIcon">
                </i></div>
               

                
                
              </div>
              
              
            </div>
            <div class="comment-text-sm comment-desc-container">
                <span class="comment-description">{{
                  comment.responseDesc
                }}</span>
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
        isPinned: false,
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
      if (this.$store.state.user.userId != 0) {
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
      } else {
        this.$router.push("/login");
      }
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
        isPinned: false,
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
      if (this.$store.state.user.userId != 0) {
        VotingService.upvoteCommentForResponse(comment)
          .then((response) => {
            console.log("upvote");
            console.log(response.data);
            this.getComments(this.post);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        this.$router.push("/login");
      }
    },
    downVote(comment) {
      if (this.$store.state.user.userId != 0) {
        VotingService.downvoteCommentForResponse(comment)
          .then((response) => {
            console.log(response.data);
            this.getComments(this.post);
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        this.$router.push("/login");
      }
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
  },

  created() {
    this.getComments(this.post);
  },
};
</script>

<style scoped>
/* General Styles */
body {
  background-color: #eee;
  display: flex;
  justify-content: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Ensure a consistent font */
}

/* Comment Section Styles */
.comments {
  text-decoration: underline;
  text-underline-position: under;
  cursor: pointer;
  margin: 0;
  padding-bottom: 1rem;
}

.comment-bottom {
  background-color: #fff;
  padding: 1rem;
  border-top: solid 1px #323232;
  margin-bottom: 1rem;
  /* Apply max width here to control the width of each comment */
  max-width: 90%;
  min-width: 90%;
  /* Center each comment */
  margin-left: auto;
  margin-right: auto;
}

.secure-pfp {
  display: flex;
}
.profile-image {
  display: block;
  justify-content: center;
  align-content: center;
}

.arrows {
  align-content: center;
}
.commentCreatorName {
  display: flex;
  flex-flow: row;
  margin: 0%;
  text-align: justify;
  font-weight: 550;
  font-size: medium;
}
.commentTime {
  font-size: smaller;
  text-align: justify;
}
.total-votes {
  margin-right: .5rem;
  align-content: center;
}
.userAndTrashContainer{
  display: flex;
  flex-flow: row;
  justify-content: space-between;
}
.user-and-time {
  display: flex;
  flex-flow: column;
  padding-bottom: .5%;
}
#trashCanIcon{
  display: flex;
  padding-left: 10px;
  
}

.fa-trash:hover {
  color: #fe7f2d;
  animation-name: fa-shake;
  animation-duration: var(--fa-animation-duration, 1s);
  animation-iteration-count: var(--fa-animation-iteration-count, infinite);
  animation-timing-function: var(--fa-animation-timing, linear);
}

.comment-description {
  display: block;
  word-wrap: break-word;

  text-align: justify;
}

.containing-picture {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-top: 0%;
}

/* Button and Icon Styles */
.btn-primary {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 0.5rem 1rem;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.upvoteIcon,
.downvoteIcon {
  cursor: pointer;
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

/* Layout and Flexbox */
.add-comment-section {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 1rem;
}

.comment-desc-container {
  padding-bottom: 0.5rem;
  padding-left: 6%;
}

.comment-container {
  width: 100%;
  max-width: 600px; /* Fixed width for the comment container */
  margin: 0 auto;
  border-top: 1px solid blueviolet;
}

.comment-container > div {
  padding-left: 1rem;
}

.dot {
  height: 0.5rem;
  width: 0.5rem;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  margin: 0 0.5rem;
}

/* Responsive Adjustments */
@media (max-width: 768px) {
  .add-comment-section {
    flex-direction: column;
    align-items: stretch;
  }

  .btn-primary {
    width: 100%;
  }

  .comment-container {
    width: 100%;
  }
}

.p-2{
  padding: .0rem !important;
}

/* Additional Styles for Image Buttons */
.image-button {
  background: none;
  border: none;
  padding: 0;
  margin: 0;
  cursor: pointer;
  display: inline-flex;
}

.image-button img {
  display: block;
  width: 1rem;
  height: auto;
}

/* Your other existing styles */
</style>