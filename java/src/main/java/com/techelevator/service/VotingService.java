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
public class VotingService implements IVotingService{

    @Autowired
    private JdbcResponseVotingDao jdbcResponseVotingDao;

    @Autowired
    private JdbcPostVotingDao jdbcPostVotingDao;

    private final JdbcTemplate jdbcTemplate;

    public VotingService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public VotingDto getVoteStatusByObject(ResponseDto responseDto, Principal principal) {

        VotingDto votingDto = jdbcResponseVotingDao.getVotingDtoForValidation(responseDto, principal);

        //Call database for getVotingDto by response object. Call from controller.

        return votingDto;
    }

    @Override
    public VotingDto upvoteCommentByObject(ResponseDto responseDto, Principal principal) {


        //Rework for return types
        VotingDto votingDto = jdbcResponseVotingDao.getVotingDtoForValidation(responseDto, principal);
        if(votingDto.getResponseUserId() == 0){
            //If this hits, no entry for comment.
            ResponseDto newResponseDto = jdbcResponseVotingDao.addEntryAndIncrementUpvote(responseDto, principal);
            return votingDto;

        } else if ((votingDto.getObjectId() == responseDto.getResponseId()) &&
                    votingDto.isVoteStatus() == true){
            //If entry exists and matches responseId, delete entry from database
            ResponseDto newResponseDto = jdbcResponseVotingDao.deleteEntryAndDecrementUpvote(responseDto, principal);
            return votingDto;


        }
        return votingDto;

    }

    //This is where the thing goes to make sure we are good to go generally speaking

    @Override
    public VotingDto downvoteCommentByObject(ResponseDto responseDto, Principal principal) {


        //Rework for return types
        VotingDto votingDto = jdbcResponseVotingDao.getVotingDtoForValidation(responseDto, principal);
        if(votingDto.getResponseUserId() == 0){
            //If this hits, no entry for comment.
            ResponseDto newResponseDto = jdbcResponseVotingDao.addEntryAndIncrementDownvote(responseDto, principal);
            return votingDto;

        } else if ((votingDto.getObjectId() == responseDto.getResponseId()) &&
                votingDto.isVoteStatus() == false){
            //If entry exists and matches responseId, delete entry from database
            ResponseDto newResponseDto = jdbcResponseVotingDao.deleteEntryAndDecrementDownvote(responseDto, principal);
            return votingDto;


        }
        return votingDto;

    }

    @Override
    public VotingDto getVoteStatusByPost(PostDto postDto, Principal principal) {

        VotingDto votingDto = jdbcPostVotingDao.getVotingDtoForValidation(postDto, principal);


        return votingDto;

    }


}
