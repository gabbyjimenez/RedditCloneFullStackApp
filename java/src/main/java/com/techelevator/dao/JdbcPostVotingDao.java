package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcPostVotingDao implements PostVotingDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostVotingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PostDto addEntryAndIncrementUpvote(PostDto postDto, Principal principal) {

        //RAW ADD NO VALIDATION
        String sqlAddToJoin = "INSERT INTO post_user_vote (toggle_status, post_id, user_id) " +
                "VALUES (true, ?, (SELECT user_id FROM users WHERE username = ?)) RETURNING post_user_vote_id;";
        String sqlIncrementResponseUpvote = "UPDATE posts " +
                "SET upvotes = upvotes+1 " +
                "WHERE post_id = ?;";
        try{
            //Insert new entry into the join with a value of TRUE
           int newJoinId = jdbcTemplate.queryForObject(sqlAddToJoin, int.class, postDto.getPostId(), principal.getName());
           //Increment upvotes by 1
           jdbcTemplate.update(sqlIncrementResponseUpvote, postDto.getPostId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return postDto;
    }

    @Override
    public PostDto deleteEntryAndDecrementUpvote(PostDto postDto, Principal principal) {
        String sqlDeleteFromJoin= "DELETE FROM post_user_vote \n" +
                "WHERE post_id = ? AND user_id = (SELECT user_id FROM users WHERE username = ?);";
        String sqlDecrementPostUpvote = "UPDATE posts " +
                "SET upvotes = upvotes-1 " +
                "WHERE post_id = ?;";
        try{
            //Insert new entry into the join with a value of TRUE
             jdbcTemplate.update(sqlDeleteFromJoin, postDto.getPostId(), principal.getName());
            //decrement upvotes by 1
            jdbcTemplate.update(sqlDecrementPostUpvote, postDto.getPostId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return postDto;
    }

    //Gets voting DTO for validaiton in service class
    public VotingDto getVotingDtoForValidation (PostDto postDto, Principal principal){
            VotingDto votingDto = new VotingDto();

        String sql = "SELECT post_user_vote_id, toggle_status, post_id, user_id " +
                "FROM post_user_vote " +
                "WHERE user_id = (SELECT user_id FROM users WHERE username = ?) AND post_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName(), postDto.getPostId());
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


    @Override
    public PostDto addEntryAndIncrementDownvote(PostDto downvotedPost, Principal principal) {

        //RAW ADD NO VALIDATION
        String sqlAddToJoin = "INSERT INTO post_user_vote (toggle_status, post_id, user_id) " +
                "VALUES (false, ?, (SELECT user_id FROM users WHERE username = ?)) RETURNING post_user_vote_id;";
        String sqlIncrementPostUpvote = "UPDATE posts " +
                "SET downvotes = downvotes+1 " +
                "WHERE post_id = ?;";
        try{
            //Insert new entry into the join with a value of FALSE
            int newJoinId = jdbcTemplate.queryForObject(sqlAddToJoin, int.class, downvotedPost.getPostId(), principal.getName());
            //Increment downvotes by 1
            jdbcTemplate.update(sqlIncrementPostUpvote, downvotedPost.getPostId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return downvotedPost;
    }

    @Override
    public PostDto deleteEntryAndDecrementDownvote(PostDto postDto, Principal principal) {
        String sqlDeleteFromJoin = "DELETE FROM post_user_vote \n" +
                "WHERE post_id = ? AND user_id = (SELECT user_id FROM users WHERE username = ?);";
        String sqlDecrementPostUpvote = "UPDATE posts " +
                "SET downvotes = downvotes-1 " +
                "WHERE post_id = ?;";
        try {
            //Delete entry
            jdbcTemplate.update(sqlDeleteFromJoin, postDto.getPostId(), principal.getName());
            //decrement downvotes by 1
            jdbcTemplate.update(sqlDecrementPostUpvote, postDto.getPostId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return postDto;

    }

    

    

    private VotingDto mapResultsToVotingDto (SqlRowSet rowSet){

        VotingDto votingDto = new VotingDto();
        votingDto.setVoteStatus(rowSet.getBoolean("toggle_status"));
        votingDto.setObjectId(rowSet.getInt("post_id"));
        votingDto.setResponseUserId(rowSet.getInt("post_user_vote_id"));
        votingDto.setUserId(rowSet.getInt("user_id"));


        return votingDto;

    }


}
