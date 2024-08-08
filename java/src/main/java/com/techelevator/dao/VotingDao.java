package com.techelevator.dao;

import com.techelevator.model.ResponseDto;

public interface VotingDao {

    //ADD UPVOTE TO RESPONSE
        //Put into responses table where responseId = x --> increment upvote by 1
            //Decrement downvote in responses table by 1 ONLY IF join table value already exists as false
        //Insert into Join table with responseId and UserId
            //If no Value exists, insert as true
            //If value exists, change to true

   ResponseDto addUpvote(ResponseDto responseDto);

    //ADD DOWNVOTE TO RESPONSE
        //Put into responose table where




    //DELETE UPVOTE FROM RESPONSE








}
