package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDenDao implements DenDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcDenDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<DenDto> retrieveAllDens(){
       List<DenDto> dens = new ArrayList<>();


       String sql = "SELECT den_id, den_name, users.username AS creator_username, creator_id " +
               "FROM dens " +
               "JOIN users ON dens.creator_id = users.user_id;";

       try{
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
           while(results.next()){
               DenDto den = mapRowToDen(results);
               dens.add(den);
           }

       } catch(CannotGetJdbcConnectionException e){
           throw new DaoException("Unable To Connect to Database", e);

       }

       return dens;
   }

    @Override
    public List<PostDto> retrievePostsByDenName(String denName) {

        List<PostDto> posts = new ArrayList<>();

        String sql = "SELECT response_id, response_desc, responses.post_id, responses.creator_id, dens.den_name, users.username AS creator_name \n" +
                "FROM responses " +
                "JOIN users ON responses.creator_id = users.user_id " +
                "JOIN posts ON responses.post_id = posts.post_id " +
                "JOIN dens ON posts.den_id = dens.den_id " +
                "WHERE dens.den_name ILIKE ? AND posts.post_id = ?";



        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, denName);
            while(results.next()){
                PostDto post = mapRowToPost(results);
                posts.add(post);
            }

        } catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable To Connect to Database", e);

        }

        return posts;
    }

    @Override
    public List<ResponseDto> retrieveResponsesByPost(int postId) {
        List<ResponseDto> responses = new ArrayList<>();

        String sql = "SELECT response_id, response_desc, post_id, creator_id, users.username AS creator_name " +
                "FROM responses " +
                "JOIN users ON responses.creator_id = users.user_id " +
                "WHERE post_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);
            while(results.next()){
                ResponseDto response = mapRowToResponse(results);
                responses.add(response);
            }
        } catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable To Connect to Database", e);
        }
        return responses;
    }
    private ResponseDto mapRowToResponse(SqlRowSet rowSet){
        ResponseDto response = new ResponseDto();
        response.setResponseId(rowSet.getInt("response_id"));
        response.setResponseDesc(rowSet.getString("response_desc"));
        response.setPostId(rowSet.getInt("post_id"));
        response.setCreatorId(rowSet.getInt("creator_id"));
        response.setCreatorName(rowSet.getString("creator_name"));
        return response;
    }



    private PostDto mapRowToPost(SqlRowSet rowSet){
        PostDto post = new PostDto();
        post.setPostId(rowSet.getInt("post_id"));
        post.setPostTitle(rowSet.getString("post_title"));
        post.setPostDesc(rowSet.getString("post_desc"));
        post.setDenId(rowSet.getInt("post_den_id"));
        post.setDenName(rowSet.getString("post_den_name"));
        post.setCreatorId(rowSet.getInt("post_creator_id"));
        post.setCreatorUsername(rowSet.getString("creator_name"));

        return post;
    }




    private DenDto mapRowToDen(SqlRowSet rowSet){
        DenDto den = new DenDto();
        den.setDenId(rowSet.getInt("den_id"));
        den.setDenName(rowSet.getString("den_name"));
        den.setDenCreatorId(rowSet.getInt("creator_id"));
        den.setDenCreatorUserName(rowSet.getString("creator_username"));
        return den;
    }


}
