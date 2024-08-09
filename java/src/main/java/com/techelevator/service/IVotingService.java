package com.techelevator.service;

import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;

import java.security.Principal;

public interface IVotingService {


     VotingDto getVoteStatusByObject(ResponseDto responseDto, Principal principal);

     VotingDto upvoteCommentByObject(ResponseDto responseDto, Principal principal);

     VotingDto downvoteCommentByObject(ResponseDto responseDto, Principal principal);













}
