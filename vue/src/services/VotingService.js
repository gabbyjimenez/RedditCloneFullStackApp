import axios from 'axios';

export default {

    retrieveVoteInformationForComments(comment){
    return axios.post(`/${comment.denName}/${comment.postId}/comments/${comment.responseId}/votes`, comment)
    }






}