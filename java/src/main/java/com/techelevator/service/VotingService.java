package com.techelevator.service;

import com.techelevator.dao.JdbcVotingDao;
import com.techelevator.dao.VotingDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;


@Component
public class VotingService implements IVotingService{

    @Autowired
    private JdbcVotingDao jdbcVotingDao;

    private final JdbcTemplate jdbcTemplate;

    public VotingService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public VotingDto getVoteStatusByObject(ResponseDto responseDto, Principal principal) {

        VotingDto votingDto = jdbcVotingDao.getVotingDtoForValidation(responseDto, principal);

        //Call database for getVotingDto by response object. Call from controller.

        return votingDto;
    }

    @Override
    public VotingDto upvoteCommentByObject(ResponseDto responseDto, Principal principal) {


        //Rework for return types
        VotingDto votingDto = jdbcVotingDao.getVotingDtoForValidation(responseDto, principal);
        if(votingDto.getResponseUserId() == 0){
            //If this hits, no entry for comment.
            ResponseDto newResponseDto = jdbcVotingDao.addEntryAndIncrementUpvote(responseDto, principal);
            return votingDto;

        } else if ((votingDto.getObjectId() == responseDto.getResponseId()) &&
                    votingDto.isVoteStatus() == true){
            //If entry exists and matches responseId, delete entry from database
            ResponseDto newResponseDto = jdbcVotingDao.deleteEntryAndDecrementUpvote(responseDto, principal);
            return votingDto;


        }
        return votingDto;

    }

    //This is where the thing goes to make sure we are good to go generally speaking

    @Override
    public VotingDto downvoteCommentByObject(ResponseDto responseDto, Principal principal) {


        //Rework for return types
        VotingDto votingDto = jdbcVotingDao.getVotingDtoForValidation(responseDto, principal);
        if(votingDto.getResponseUserId() == 0){
            //If this hits, no entry for comment.
            ResponseDto newResponseDto = jdbcVotingDao.addEntryAndIncrementDownvote(responseDto, principal);
            return votingDto;

        } else if ((votingDto.getObjectId() == responseDto.getResponseId()) &&
                votingDto.isVoteStatus() == false){
            //If entry exists and matches responseId, delete entry from database
            ResponseDto newResponseDto = jdbcVotingDao.deleteEntryAndDecrementDownvote(responseDto, principal);
            return votingDto;


        }
        return votingDto;

    }





}
