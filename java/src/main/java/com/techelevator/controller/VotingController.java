package com.techelevator.controller;


import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;
import com.techelevator.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping(path = "/{den}/{id}/comments/{responseId}/votes")
    public VotingDto retrieveVoteInformationForComments(@RequestBody ResponseDto responseDto, Principal principal, @PathVariable("den") String denName, @PathVariable("id") int postId, @PathVariable("responseId") int responseId){

        //KEY GETTER FOR COMMENT UPVOTE AND DOWNVOTE INFORMATION

        return votingService.getVoteStatusByObject(responseDto, principal);
        //Main filter is by responseStatusId. If 0, entry has not been made into database yet.
    }

    @PostMapping(path = "/{den}/{id}/comments/{responseId}/upvote")
    public VotingDto upvoteCommentForResponse(@RequestBody ResponseDto responseToUpvote, Principal principal, @PathVariable("den") String denName, @PathVariable("id") int postId, @PathVariable("responseId") int responseId){



        return votingService.upvoteCommentByObject(responseToUpvote, principal);

    }


    @PostMapping(path = "/{den}/{id}/comments/{responseId}/downvote")
    public VotingDto downvoteCommentForResponse(@RequestBody ResponseDto responseToDownvote, Principal principal, @PathVariable("den") String denName, @PathVariable("id") int postId, @PathVariable("responseId") int responseId){



//        return votingService.upvoteCommentByObject(responseToUpvote, principal);
        return votingService.downvoteCommentByObject(responseToDownvote, principal);

    }

    @PostMapping(path = "/{den}/{id}/votes")
    public VotingDto retrieveVoteInformationForPosts(@RequestBody PostDto postDto, Principal principal, @PathVariable("den") String denName, @PathVariable("id") int postId){


        return votingService.getVoteStatusByPost(postDto, principal);


    }

































}
