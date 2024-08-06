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
    return axios.get(`${post.denName}/${post.id}/comments`);
  }

}