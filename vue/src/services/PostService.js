import axios from 'axios';

export default {

  getPosts() {
    return axios.get('/post');
  },

//   getTopic(topicId) {
//     return axios.get(`/post/${postId}`)
//   },

}