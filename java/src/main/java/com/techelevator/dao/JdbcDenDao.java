package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;



@Component
public class JdbcDenDao implements DenDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcDenDao(JdbcTemplate jdbcTemplate,  UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
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

        String sql = "SELECT post_id, post_title, post_desc, posts.den_id AS post_den_id, dens.den_name AS post_den_name, users.username AS creator_name, posts.creator_id AS post_creator_id, upvotes, downvotes, pinned, posts.time_created, users.pfp_link FROM posts " +
                "JOIN users ON posts.creator_id = users.user_id " +
                "JOIN dens ON posts.den_id = dens.den_id " +
                "WHERE dens.den_name ILIKE ?" +
                "ORDER BY pinned DESC, upvotes DESC, time_created DESC";


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

        String sql = "SELECT response_id, response_desc, responses.post_id, responses.creator_id, dens.den_name, users.username AS creator_name, responses.upvotes, responses.downvotes, responses.pinned, responses.time_created, users.pfp_link " +
                "FROM responses " +
                "JOIN users ON responses.creator_id = users.user_id " +
                "JOIN posts ON responses.post_id = posts.post_id " +
                "JOIN dens ON posts.den_id = dens.den_id " +
                "WHERE dens.den_name ILIKE ? AND posts.post_id = ? " +
                "ORDER BY pinned DESC, upvotes DESC, time_created DESC";

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

        String sql = "INSERT INTO responses (response_desc, post_id, creator_id, time_created) " +
                "VALUES (?, ?, ?, CURRENT_TIMESTAMP(0)) " +
                "RETURNING response_id";


        try{
            int newResponseId = jdbcTemplate.queryForObject(sql, int.class, newResponse.getResponseDesc(), newResponse.getPostId(), newResponse.getCreatorId());
            newResponse.setResponseId(newResponseId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        List<User> users = userDao.getUsers();
        for (User user : users){
            if(newResponse.getResponseDesc().contains("@"+ user.getUsername() + " ")){
                if(user.getPhoneNumber() != null) {
                    if(!user.getPhoneNumber().equalsIgnoreCase("0000000000")){
                        String smsBody = "You have been tagged in a comment on Foxtrot! Log in to check it out!";
                        String phoneNumber = user.getPhoneNumber();
                        Twilio.init("ACec4c4a1c09b9e3b0c85856282ee18290", "4ca9ba626dd3dc21f57acf1476350c44");
                        Message message = Message.creator(
                                        new com.twilio.type.PhoneNumber("+1" + phoneNumber),
                                        new com.twilio.type.PhoneNumber("+18559611686"),
                                        smsBody)
                                .create();
                    }
                }
            }
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

        String sqlOne = "DELETE FROM favorites_dens \n" +
                "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?);";

        String sqlTwo = "DELETE FROM responses \n" +
                "WHERE post_id IN (\n" +
                "    SELECT post_id FROM  posts WHERE den_id = (\n" +
                "        SELECT den_id FROM dens WHERE den_name = ?" +
                "    )\n" +
                ");";

        String sqlThree = "DELETE FROM posts\n" +
                "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?);";

        String sqlFour = "DELETE FROM den_category\n" +
                "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?);";

        String sqlFive = "DELETE FROM dens \n" +
                "WHERE den_name = ?;";

        jdbcTemplate.update(sqlOne, denName);
        jdbcTemplate.update(sqlTwo, denName);
        jdbcTemplate.update(sqlThree, denName);
        jdbcTemplate.update(sqlFour, denName);
        jdbcTemplate.update(sqlFive, denName);

    }

    @Override
    public void deletePostByPostId(int postId) {

        String sqlOne = "DELETE FROM post_user_vote\n" +
                "WHERE post_id = ?";

        String sqlTwo = "DELETE FROM responses\n" +
                "WHERE post_id = ?";

        String sqlThree = "DELETE FROM posts\n" +
                "WHERE post_id = ?";

        jdbcTemplate.update(sqlOne, postId);
        jdbcTemplate.update(sqlTwo, postId);
        jdbcTemplate.update(sqlThree, postId);
    }

    @Override
    public void deleteCommentByCommentId(int responseId) {

        String sqlOne = "DELETE FROM response_user_vote WHERE response_id = ?";

        String sqlTwo = "DELETE FROM responses WHERE response_id = ?";

        jdbcTemplate.update(sqlOne, responseId);
        jdbcTemplate.update(sqlTwo, responseId);

    }

    @Override
    public List<DenDto> getFavoritesByUsername(String username) {
        List<DenDto> favoritesList = new ArrayList<>();

        String sql = "SELECT dens.den_id, den_name, den_desc, users.username AS creator_username, creator_id " +
                "FROM dens " +
                "JOIN users ON dens.creator_id = users.user_id " +
                "JOIN favorites_dens ON dens.den_id = favorites_dens.den_id " +
                "WHERE favorites_dens.user_id = (SELECT user_id FROM users WHERE username = ?)";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()){
                favoritesList.add(mapRowToDen(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
        }

        return favoritesList;
    }


    public FavoriteDto getFavoriteByUserAndDenName (String denName, String userName){


        FavoriteDto favoriteDto = new FavoriteDto();


        String sql = "SELECT * FROM favorites_dens\n" +
                "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?) AND user_id = (SELECT user_id FROM users WHERE username = ?)";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, denName, userName);
            if(results.next()){
                favoriteDto = mapRowToFavorite(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return favoriteDto;

    }




    @Override
    public void toggleFavorite(String denName, Principal principal) {

        FavoriteDto compareToThis = getFavoriteByUserAndDenName(denName, principal.getName());

        String sql = "";


        if(compareToThis.isToggleStatus() == false && compareToThis.getFavoriteId() == 0){

            //Insert New Entry
            sql = "INSERT INTO favorites_dens (toggle_status, den_id, user_id) " +
                    "VALUES (true, (SELECT den_id FROM dens WHERE den_name = ?), (SELECT user_id FROM users WHERE username = ?)) " +
                    "RETURNING favorites_dens_id ";

            try{

                int newId = jdbcTemplate.queryForObject(sql, int.class, denName, principal.getName());

            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }


        } else if (compareToThis.isToggleStatus() == true && compareToThis.getFavoriteId() != 0){
            //DELETE ENTRY

            sql = "DELETE FROM favorites_dens " +
                    "WHERE den_id = (SELECT den_id FROM dens WHERE den_name = ?) AND user_id = (SELECT user_id FROM users WHERE username = ?)";

            try{

               jdbcTemplate.update(sql, denName, principal.getName());

            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }

        }


    }

    @Override
    public PostDto createNewPost(PostDto newPost) {

        String sql = "INSERT INTO posts (post_title, post_desc, den_id, creator_id, time_created) " +
                "VALUES (?, ?, (SELECT den_id FROM dens WHERE den_name = ?), ?, CURRENT_TIMESTAMP(0)) " +
                "RETURNING post_id";

        try{
            int newPostId = jdbcTemplate.queryForObject(sql, int.class, newPost.getPostTitle(), newPost.getPostDesc(), newPost.getDenName(), newPost.getCreatorId());
            newPost.setPostId(newPostId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        //Send text message


        List<User> users = userDao.getUsers();
        for (User user : users){
            if(newPost.getPostDesc().contains("@"+ user.getUsername() + " ")){
                if(user.getPhoneNumber()!=null){
                    if(user.getPhoneNumber().equalsIgnoreCase("0000000000")){
                        String smsBody = "You have been tagged in a post on Foxtrot! Log in to check it out!";
                        String phoneNumber = user.getPhoneNumber();
                        Twilio.init("ACec4c4a1c09b9e3b0c85856282ee18290", "4ca9ba626dd3dc21f57acf1476350c44");
                        Message message = Message.creator(
                                        new com.twilio.type.PhoneNumber("+1"+phoneNumber),
                                        new com.twilio.type.PhoneNumber("+18559611686"),
                                        smsBody)
                                .create();
                    }
                }
            }
        }


        return newPost;
    }

    public PostDto pinPost(PostDto postToPin){

        String sql = "";

        if (postToPin.isPinned() == true){
            sql = "UPDATE posts " +
                    "SET pinned = false " +
                    "WHERE post_id = ?";
        } else {
            sql = "UPDATE posts " +
                    "SET pinned = true " +
                    "WHERE post_id = ?";
        }

        try{
            jdbcTemplate.update(sql, postToPin.getPostId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return postToPin;


    }

    @Override
    public ResponseDto pinResponse(ResponseDto responseToPin) {
        String sql = "";

        if (responseToPin.isPinned() == true){
            sql = "UPDATE responses " +
                    "SET pinned = false " +
                    "WHERE response_id = ?";
        } else {
            sql = "UPDATE response " +
                    "SET pinned = true " +
                    "WHERE response_id = ?";
        }

        try{
            jdbcTemplate.update(sql, responseToPin.getResponseId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return responseToPin;

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
        response.setPinned(rowSet.getBoolean("pinned"));
        response.setTimeCreated(rowSet.getTimestamp("time_created").toLocalDateTime());
        response.setPfpUrl(rowSet.getString("pfp_link"));
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
        post.setPinned(rowSet.getBoolean("pinned"));
        post.setTimeCreated(rowSet.getTimestamp("time_created").toLocalDateTime());
        post.setPfpUrl(rowSet.getString("pfp_link"));

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


    private FavoriteDto mapRowToFavorite(SqlRowSet rowSet){
        FavoriteDto favoriteDto = new FavoriteDto();
        favoriteDto.setDenId(rowSet.getInt("den_id"));
        favoriteDto.setFavoriteId(rowSet.getInt("favorites_dens_id"));
        favoriteDto.setToggleStatus(rowSet.getBoolean("toggle_status"));
        favoriteDto.setUserId(rowSet.getInt("user_id"));
        return favoriteDto;
    }



}
