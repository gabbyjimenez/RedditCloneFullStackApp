package com.techelevator.service;

import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;

import java.security.Principal;

public interface IVotingService {


     VotingDto getVoteStatusByObject(ResponseDto responseDto, Principal principal);


     VotingDto getVoteStatusByPost(PostDto postDto, Principal principal);

     VotingDto addVoteForPost(PostDto postDto, Principal principal, boolean isUpvote);


     VotingDto addVoteForResponse(ResponseDto responseDto, Principal principal, boolean isUpvote);



}
