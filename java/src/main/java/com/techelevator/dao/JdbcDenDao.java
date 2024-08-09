package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CategoryDTO;
import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
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


       String sql = "SELECT den_id, den_name, den_desc, users.username AS creator_username, creator_id " +
               "FROM dens " +
               "JOIN users ON dens.creator_id = users.user_id;";


       try{
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
           while(results.next()){
               DenDto den = mapRowToDen(results);
               den.setCategoryNames(retrieveCategoriesForDen(den.getDenId()));
               dens.add(den);
           }

       } catch(CannotGetJdbcConnectionException e){
           throw new DaoException("Unable To Connect to Database", e);

       }

       return dens;
   }


   @Override
   public List<CategoryDTO> retrieveAllCategories() {
       List<CategoryDTO> categories = new ArrayList<>();

       String sql = "SELECT category_id, category_name FROM categories";

       try{
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
           while(results.next()){
               CategoryDTO category = mapRowToCategory(results);
               categories.add(category);
           }

       } catch(CannotGetJdbcConnectionException e){
           throw new DaoException("Unable To Connect to Database", e);

       }

       return categories;
   }

    @Override
    public List<PostDto> retrievePostsByDenName(String denName) {

        List<PostDto> posts = new ArrayList<>();

        String sql = "SELECT post_id, post_title, post_desc, posts.den_id AS post_den_id, dens.den_name AS post_den_name, users.username AS creator_name, posts.creator_id AS post_creator_id, upvotes, downvotes FROM posts " +
                "JOIN users ON posts.creator_id = users.user_id " +
                "JOIN dens ON posts.den_id = dens.den_id " +
                "WHERE dens.den_name ILIKE ?";


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
    public List<ResponseDto> retrieveResponsesByPost(String denName, int postId) {
        List<ResponseDto> responses = new ArrayList<>();

        String sql = "SELECT response_id, response_desc, responses.post_id, responses.creator_id, dens.den_name, users.username AS creator_name, responses.upvotes, responses.downvotes " +
                "FROM responses " +
                "JOIN users ON responses.creator_id = users.user_id " +
                "JOIN posts ON responses.post_id = posts.post_id " +
                "JOIN dens ON posts.den_id = dens.den_id " +
                "WHERE dens.den_name ILIKE ? AND posts.post_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, denName, postId);
            while(results.next()){
                ResponseDto response = mapRowToResponse(results);
                responses.add(response);
            }
        } catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable To Connect to Database", e);
        }
        return responses;
    }

    @Override
    public ResponseDto createNewResponse(ResponseDto newResponse) {

        String sql = "INSERT INTO responses (response_desc, post_id, creator_id) " +
                "VALUES (?, ?, ?) " +
                "RETURNING response_id";


        try{
            int newResponseId = jdbcTemplate.queryForObject(sql, int.class, newResponse.getResponseDesc(), newResponse.getPostId(), newResponse.getCreatorId());
            newResponse.setResponseId(newResponseId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newResponse;

    }


    @Override
    public List<String> retrieveCategoriesForDen(int denId) {

        List<String> categories = new ArrayList<>();

        String sql = "SELECT category_name \n" +
                "FROM categories\n" +
                "JOIN den_category ON categories.category_id = den_category.category_id\n" +
                "JOIN dens ON den_category.den_id = dens.den_id\n" +
                "WHERE dens.den_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, denId);
            while(results.next()){
                categories.add(results.getString("category_name"));

            }
        } catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable To Connect to Database", e);
        }

        return categories;
    }

    @Override
    public DenDto createNewDen(DenDto newDen) {

       String sqlDen = "INSERT INTO dens (den_name, den_desc, creator_id) " +
               "VALUES (?, ?, ?) " +
               "RETURNING den_id;";

       String sqlJoin = "INSERT INTO den_category (den_id, category_id) " +
               "VALUES (?, (SELECT category_id FROM categories WHERE category_name = ?)) " +
               "RETURNING group_category_id;";


        try{

            //Get Everything from Den Object, Returning new ID
            int newDenId = jdbcTemplate.queryForObject(sqlDen, int.class, newDen.getDenName(), newDen.getDenDesc(), newDen.getDenCreatorId());
            newDen.setDenId(newDenId);

            for(String category : newDen.getCategoryNames()){
                int entryAddedId = jdbcTemplate.queryForObject(sqlJoin, int.class, newDen.getDenId(), category);
            }


            //Add to categories Join Table, Use newDen.getDenID to add to Join Table on category.

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newDen;
    }

    @Override
    public void deleteDenByDenName(String denName) {

        String sql = "DELETE FROM responses \n" +
                "WHERE post_id IN (\n" +
                "    SELECT post_id FROM  posts WHERE den_id = (\n" +
                "        SELECT den_id FROM dens WHERE den_name = ?" +
                "    )\n" +
                ");";

        String sqlTwo = "DELETE FROM posts\n" +
                "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?);";

        String sqlThree = "DELETE FROM den_category\n" +
                "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?);";

        String sqlFour = "DELETE FROM dens \n" +
                "WHERE den_name = ?;";

        jdbcTemplate.update(sql, denName);
        jdbcTemplate.update(sqlTwo, denName);
        jdbcTemplate.update(sqlThree, denName);
        jdbcTemplate.update(sqlFour, denName);

    }

    @Override
    public void deletePostByPostId(int postId) {

        String sql = "DELETE FROM responses\n" +
                "WHERE post_id = ?";

        String sqlTwo = "DELETE FROM posts\n" +
                "WHERE post_id = ?";

        jdbcTemplate.update(sql, postId);
        jdbcTemplate.update(sqlTwo, postId);

    }

    @Override
    public void deleteCommentByCommentId(int responseId) {

        String sql = "DELETE FROM responses WHERE response_id = ?";

        jdbcTemplate.update(sql, responseId);

    }

    @Override
    public PostDto createNewPost(PostDto newPost) {

        String sql = "INSERT INTO posts (post_title, post_desc, den_id, creator_id) " +
                "VALUES (?, ?, (SELECT den_id FROM dens WHERE den_name = ?), ?) " +
                "RETURNING post_id";

        try{
            int newPostId = jdbcTemplate.queryForObject(sql, int.class, newPost.getPostTitle(), newPost.getPostDesc(), newPost.getDenName(), newPost.getCreatorId());
            newPost.setPostId(newPostId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newPost;
    }

    private ResponseDto mapRowToResponse(SqlRowSet rowSet){
        ResponseDto response = new ResponseDto();
        response.setResponseId(rowSet.getInt("response_id"));
        response.setResponseDesc(rowSet.getString("response_desc"));
        response.setPostId(rowSet.getInt("post_id"));
        response.setCreatorId(rowSet.getInt("creator_id"));
        response.setDenName(rowSet.getString("den_name"));
        response.setCreatorName(rowSet.getString("creator_name"));
        response.setUpvotes(rowSet.getInt("upvotes"));
        response.setDownvotes(rowSet.getInt("downvotes"));
        return response;
    }

    private CategoryDTO mapRowToCategory(SqlRowSet rowSet) {
        CategoryDTO category = new CategoryDTO();
        category.setCategoryId(rowSet.getInt("category_id"));
        category.setCategoryName(rowSet.getString("category_name"));
        return category;
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
        post.setUpvotes(rowSet.getInt("upvotes"));
        post.setDownvotes(rowSet.getInt("downvotes"));

        return post;
    }




    private DenDto mapRowToDen(SqlRowSet rowSet){
        DenDto den = new DenDto();
        den.setDenId(rowSet.getInt("den_id"));
        den.setDenName(rowSet.getString("den_name"));
        den.setDenCreatorId(rowSet.getInt("creator_id"));
        den.setDenCreatorUserName(rowSet.getString("creator_username"));
        den.setDenDesc(rowSet.getString("den_desc"));
        return den;
    }


}
