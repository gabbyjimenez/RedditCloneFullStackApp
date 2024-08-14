import axios from 'axios';

export default {

  getPosts(denName) {
    return axios.get(`${denName}/posts`);
  },

  makeNewPost(newPost){

    return axios.post(`${newPost.denName}/posts`, newPost);

  },

  deletePost(post){
    return axios.delete(`${post.denName}/${post.postId}`);
  },

  getComments(post){
    return axios.get(`${post.denName}/${post.postId}/comments`);
  },

  addComment(newComment){
    return axios.post(`${newComment.denName}/${newComment.postId}/comments`, newComment)
  },

  deleteComment(denName, comment){
    return axios.delete(`${denName}/${comment.postId}/comments/${comment.responseId}`, comment)

  },

  getPfpLink(userName){
    return axios.get(`${userName}/photo`);
  }

}