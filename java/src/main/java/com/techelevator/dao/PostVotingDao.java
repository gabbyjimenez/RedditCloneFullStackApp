package com.techelevator.dao;

import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;

import java.security.Principal;

public interface PostVotingDao {

   PostDto addEntryAndIncrementVote(PostDto postDto, Principal principal, boolean isUpvote);

   PostDto deleteEntryAndDecrement(PostDto postDto, Principal principal, int decrementCount, boolean decrementUpvote, boolean upvoteActive, boolean downvoteActive);

   VotingDto getVotingDtoForValidation (PostDto postDto, Principal principal);




}
