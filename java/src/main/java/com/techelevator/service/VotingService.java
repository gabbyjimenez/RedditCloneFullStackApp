package com.techelevator.service;

import com.techelevator.dao.JdbcPostVotingDao;
import com.techelevator.dao.JdbcResponseVotingDao;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;


@Component
public class VotingService implements IVotingService {

    @Autowired
    private JdbcResponseVotingDao jdbcResponseVotingDao;

    @Autowired
    private JdbcPostVotingDao jdbcPostVotingDao;

    private final JdbcTemplate jdbcTemplate;

    public VotingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public VotingDto getVoteStatusByObject(ResponseDto responseDto, Principal principal) {

        VotingDto votingDto = jdbcResponseVotingDao.getVotingDtoForValidation(responseDto, principal);

        //Call database for getVotingDto by response object. Call from controller.

        return votingDto;
    }

    public VotingDto addVoteForResponse(ResponseDto responseDto, Principal principal, boolean isUpvote){

        VotingDto votingDto = jdbcResponseVotingDao.getVotingDtoForValidation(responseDto, principal);
        // Case 1: User has not voted yet & has clicked the downvote button
        if(votingDto.getResponseUserId() == 0 && isUpvote == false){
            jdbcResponseVotingDao.addEntryAndIncrementVote(responseDto, principal, false);
            // Case 2: User has not voted yet & has clicked the upvote button
        } if(votingDto.getResponseUserId() == 0 && isUpvote == true){
            jdbcResponseVotingDao.addEntryAndIncrementVote(responseDto, principal, true);
            //Case 3: User has previously downvoted & has clicked the downvote button again
        } else if  ((votingDto.getObjectId() == responseDto.getPostId()) && votingDto.isVoteStatus() == false && isUpvote == false){
            jdbcResponseVotingDao.deleteEntryAndDecrement(responseDto, principal,1, false,false,false);
            // Case 4: User has previously upvoted & has clicked the downvote button
        } else if  ((votingDto.getObjectId() == responseDto.getPostId()) && votingDto.isVoteStatus() == true && isUpvote == false) {
            jdbcResponseVotingDao.deleteEntryAndDecrement(responseDto, principal,1, true,true,false);
            // Case 5: User has previously upvoted & has clicked the upvote button again
        } else if ((votingDto.getObjectId() == responseDto.getPostId()) && votingDto.isVoteStatus() == true && isUpvote == true){
            jdbcResponseVotingDao.deleteEntryAndDecrement(responseDto, principal,1, true,false,false);
            // Case 6: User has previously downvoted & has clicked the upvote button
        } else if ((votingDto.getObjectId() == responseDto.getPostId()) && votingDto.isVoteStatus() == false && isUpvote == true) {
            jdbcResponseVotingDao.deleteEntryAndDecrement(responseDto, principal,1, false,false,true);

        }

        return votingDto;
    }









    @Override
    public VotingDto getVoteStatusByPost(PostDto postDto, Principal principal) {

        VotingDto votingDto = jdbcPostVotingDao.getVotingDtoForValidation(postDto, principal);

        return votingDto;

    }



    public VotingDto addVoteForPost(PostDto postDto, Principal principal, boolean isUpvote){

        VotingDto votingDto = jdbcPostVotingDao.getVotingDtoForValidation(postDto, principal);
        // Case 1: User has not voted yet & has clicked the downvote button
        if(votingDto.getResponseUserId() == 0 && isUpvote == false){
            jdbcPostVotingDao.addEntryAndIncrementVote(postDto, principal, false);
            // Case 2: User has not voted yet & has clicked the upvote button
        } if(votingDto.getResponseUserId() == 0 && isUpvote == true){
            jdbcPostVotingDao.addEntryAndIncrementVote(postDto, principal, true);
            //Case 3: User has previously downvoted & has clicked the downvote button again
        } else if  ((votingDto.getObjectId() == postDto.getPostId()) && votingDto.isVoteStatus() == false && isUpvote == false){
            jdbcPostVotingDao.deleteEntryAndDecrement(postDto, principal,1, false,false,false);
            // Case 4: User has previously upvoted & has clicked the downvote button
        } else if  ((votingDto.getObjectId() == postDto.getPostId()) && votingDto.isVoteStatus() == true && isUpvote == false) {
            jdbcPostVotingDao.deleteEntryAndDecrement(postDto, principal,1, true,true,false);
            // Case 5: User has previously upvoted & has clicked the upvote button again
        } else if ((votingDto.getObjectId() == postDto.getPostId()) && votingDto.isVoteStatus() == true && isUpvote == true){
            jdbcPostVotingDao.deleteEntryAndDecrement(postDto, principal,1, true,false,false);
            // Case 6: User has previously downvoted & has clicked the upvote button
        } else if ((votingDto.getObjectId() == postDto.getPostId()) && votingDto.isVoteStatus() == false && isUpvote == true) {
            jdbcPostVotingDao.deleteEntryAndDecrement(postDto, principal,1, false,false,true);

        }

        return votingDto;
    }


}