package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcResponseVotingDao implements ResponseVotingDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcResponseVotingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ResponseDto addEntryAndIncrementVote(ResponseDto responseDto, Principal principal, boolean isUpvote) {
        // Case 1 & Case 2: User has not voted yet
        String sqlAddToJoin = "";
        String sqlIncrementResponseUpvote = "";
        if(isUpvote){

            sqlAddToJoin = "INSERT INTO response_user_vote (toggle_status, response_id, user_id) " +
                    "VALUES (true, ?, (SELECT user_id FROM users WHERE username = ?)) RETURNING response_user_vote_id;";
            sqlIncrementResponseUpvote = "UPDATE responses " +
                    "SET upvotes = upvotes +1 " +
                    "WHERE response_id = ?;";
        } else{
            sqlAddToJoin = "INSERT INTO response_user_vote (toggle_status, response_id, user_id) " +
                    "VALUES (false, ?, (SELECT user_id FROM users WHERE username = ?)) RETURNING response_user_vote_id;";
            sqlIncrementResponseUpvote = "UPDATE responses " +
                    "SET downvotes = downvotes +1 " +
                    "WHERE response_id = ?;";
        }
        //RAW ADD NO VALIDATION

        try{
            //Insert new entry into the join with a value of TRUE
            int newJoinId = jdbcTemplate.queryForObject(sqlAddToJoin, int.class, responseDto.getResponseId(), principal.getName());
            //Increment upvotes by 1
            jdbcTemplate.update(sqlIncrementResponseUpvote, responseDto.getResponseId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return responseDto;
    }


    @Override
    public ResponseDto deleteEntryAndDecrement(ResponseDto responseDto, Principal principal, int decrementCount, boolean decrementUpvote, boolean upvoteActive, boolean downvoteActive) {
        //Case 3-6: User has previously voted
        String sqlDeleteFromJoin= "DELETE FROM response_user_vote " +
                "WHERE response_id = ? AND user_id = (SELECT user_id FROM users WHERE username = ?);";
        String sqlDecrementResponseUpvote="";

        if(decrementUpvote){
            sqlDecrementResponseUpvote = "UPDATE responses " +
                    "SET upvotes = upvotes - 1 " +
                    "WHERE response_id = ?;";
        }else{
            sqlDecrementResponseUpvote = "UPDATE responses " +
                    "SET downvotes = downvotes - 1 " +
                    "WHERE response_id = ?;";
        }

        try{
            //Insert new entry into the join with a value of TRUE
            jdbcTemplate.update(sqlDeleteFromJoin, responseDto.getResponseId(), principal.getName());
            //decrement upvotes by 1
            jdbcTemplate.update(sqlDecrementResponseUpvote, responseDto.getResponseId());
            if (upvoteActive){
                addEntryAndIncrementVote(responseDto, principal, false);
            }
            if(downvoteActive){
                addEntryAndIncrementVote(responseDto, principal, true);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return responseDto;
    }






    //Gets voting DTO for validaiton in service class
    public VotingDto getVotingDtoForValidation (ResponseDto responseDto, Principal principal){
            VotingDto votingDto = new VotingDto();

        String sql = "SELECT response_user_vote_id, toggle_status, response_id, user_id " +
                "FROM response_user_vote " +
                "WHERE user_id = (SELECT user_id FROM users WHERE username = ?) AND response_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName(), responseDto.getResponseId());
            if(results.next()){
                votingDto = mapResultsToVotingDto(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return votingDto;
    }






    private VotingDto mapResultsToVotingDto (SqlRowSet rowSet){

        VotingDto votingDto = new VotingDto();
        votingDto.setVoteStatus(rowSet.getBoolean("toggle_status"));
        votingDto.setObjectId(rowSet.getInt("response_id"));
        votingDto.setResponseUserId(rowSet.getInt("response_user_vote_id"));
        votingDto.setUserId(rowSet.getInt("user_id"));

        return votingDto;

    }


}
