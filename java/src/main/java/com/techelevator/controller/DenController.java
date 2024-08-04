package com.techelevator.controller;


import com.techelevator.dao.DenDao;
import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;
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











}
