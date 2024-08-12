import axios from 'axios';

export default {

    // path="/{den}/{id}/pin"

    pinToggle(post){
        return axios.post(`${post.denName}/${post.postId}/pin`, post);
      },

}