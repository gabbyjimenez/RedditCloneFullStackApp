import axios from 'axios';

export default {

  getPosts(denName) {
    return axios.get(`${denName}/posts`);
  }

//   getTopic(topicId) {
//     return axios.get(`/post/${postId}`)
//   },

}