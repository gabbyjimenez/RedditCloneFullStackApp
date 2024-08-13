package com.techelevator.dao;

import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;

import java.security.Principal;

public interface ResponseVotingDao {


   ResponseDto addEntryAndIncrementVote(ResponseDto responseDto, Principal principal, boolean isUpvote);

   ResponseDto deleteEntryAndDecrement(ResponseDto responseDto, Principal principal, int decrementCount, boolean decrementUpvote, boolean upvoteActive, boolean downvoteActive);

   VotingDto getVotingDtoForValidation (ResponseDto responseDto, Principal principal);




}
