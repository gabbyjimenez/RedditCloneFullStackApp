package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Den;
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



    public List<Den> retrieveAllDens(){
       List<Den> dens = new ArrayList<>();


       String sql = "SELECT den_id, den_name, users.username AS creator_username, users.user_id " +
               "FROM dens " +
               "JOIN users ON dens.creator_id = users.user_id;";

       try{
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
           while(results.next()){
               Den den = mapRowToDen(results);
               dens.add(den);
           }

       } catch(CannotGetJdbcConnectionException e){
           throw new DaoException("Unable To Connect to Database", e);

       }

       return dens;
   };










    private Den mapRowToDen(SqlRowSet rowSet){
        Den den = new Den();
        den.setDenId(rowSet.getInt("den_id"));
        den.setDenName(rowSet.getString("den_name"));
        den.setDenCreatorId(rowSet.getInt("creator_id"));
        den.setDenCreatorUserName(rowSet.getString("creator_userName"));
        return den;
    }


}
