package com.techelevator.dao;

import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;

import java.util.List;

public interface DenDao {

    List<DenDto> retrieveAllDens();

    List<PostDto> retrievePostsByDenName(String denName);











}
