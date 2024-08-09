package com.techelevator.dao;

import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;

import java.security.Principal;

public interface PostVotingDao {

    //ADD UPVOTE TO RESPONSE
        //Put into responses table where responseId = x --> increment upvote by 1
            //Decrement downvote in responses table by 1 ONLY IF join table value already exists as false
        //Insert into Join table with responseId and UserId
            //If no Value exists, insert as true
            //If value exists, change to true

   PostDto addEntryAndIncrementUpvote(PostDto postDto, Principal principal);

   PostDto deleteEntryAndDecrementUpvote(PostDto postDto, Principal principal);

   VotingDto getVotingDtoForValidation (PostDto postDto, Principal principal);


   PostDto addEntryAndIncrementDownvote(PostDto downvotedPost, Principal principal);

   PostDto deleteEntryAndDecrementDownvote(PostDto postDto, Principal principal);








}
