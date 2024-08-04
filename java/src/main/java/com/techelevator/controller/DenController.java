package com.techelevator.controller;


import com.techelevator.dao.DenDao;
import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class DenController {

    @Autowired
    private DenDao denDao;

    @PreAuthorize("permitAll()")
    @GetMapping (path = "/dens")
    public List<DenDto> retrieveAllDens(){
        List<DenDto> dens = denDao.retrieveAllDens();
        return dens;
    }


    @GetMapping (path = "/{den}/posts")
    public List<PostDto> retrievePostsByDenName(@PathVariable("den") String denName){
        List<PostDto> posts = denDao.retrievePostsByDenName(denName);
        return posts;
    }

    @GetMapping(path = "/{den}/{id}/comments")
    public List<ResponseDto> retrieveResponsesByPost(@PathVariable("den") String denName, @PathVariable("id") int postId){
        List<ResponseDto> responses = denDao.retrieveResponsesByPost(denName, postId);
        return responses;
    }











}
