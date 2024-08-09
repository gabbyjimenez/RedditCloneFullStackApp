import axios from 'axios';

export default {

    retrieveVoteInformationForComments(comment){
    return axios.post(`/${comment.denName}/${comment.postId}/comments/${comment.responseId}/votes`, comment)
    },

    upvoteCommentForResponse(comment){
        return axios.post(`/${comment.denName}/${comment.postId}/comments/${comment.responseId}/upvote`, comment)
    },

    downvoteCommentForResponse(comment){
        return axios.post(`/${comment.denName}/${comment.postId}/comments/${comment.responseId}/downvote`, comment)
    },

    retrieveVoteInformationForPosts(post){
        return axios.post(`/${post.denName}/${post.postId}/votes`, post)
    },
    makeUpvoteForPost(post){
        return axios.post(`/${post.denName}/${post.postId}/upvote`, post)
    },

    makeDownvoteForPost(post){
        return axios.post(`/${post.denName}/${post.postId}/downvote`, post)
    },







}