package com.techelevator.dao;

import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;

import java.security.Principal;

public interface VotingDao {

    //ADD UPVOTE TO RESPONSE
        //Put into responses table where responseId = x --> increment upvote by 1
            //Decrement downvote in responses table by 1 ONLY IF join table value already exists as false
        //Insert into Join table with responseId and UserId
            //If no Value exists, insert as true
            //If value exists, change to true

   ResponseDto addEntryAndIncrementUpvote(ResponseDto responseDto, Principal principal);

   ResponseDto deleteEntryAndDecrementUpvote(ResponseDto responseDto, Principal principal);

   VotingDto getVotingDtoForValidation (ResponseDto responseDto, Principal principal);


   ResponseDto addEntryAndIncrementDownvote(ResponseDto downvotedResponse, Principal principal);

   ResponseDto deleteEntryAndDecrementDownvote(ResponseDto responseDto, Principal principal);








}
