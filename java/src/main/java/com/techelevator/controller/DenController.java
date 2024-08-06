package com.techelevator.controller;


import com.techelevator.dao.DenDao;
import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/dens")
    public DenDto createNewDen(@RequestBody DenDto den){
        return denDao.createNewDen(den);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{den}")
    public void deleteDenByDenName(@PathVariable("den") String denName){

    }

    @GetMapping (path = "/{den}/posts")
    public List<PostDto> retrievePostsByDenName(@PathVariable("den") String denName){
        List<PostDto> posts = denDao.retrievePostsByDenName(denName);
        return posts;
    }


    //DO I NEED PATHVARIABLE ON THIS IF INSERTED FROM THE FRONT END?
    //SQL WOULD HAVE TO BE SUB SELECT WHERE ID = DENNAME
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{den}/posts")
    public PostDto createNewPost(@RequestBody PostDto newPost){

        return denDao.createNewPost(newPost);

    }

    @GetMapping(path = "/{den}/{id}/comments")
    public List<ResponseDto> retrieveResponsesByPost(@PathVariable("den") String denName, @PathVariable("id") int postId){
        List<ResponseDto> responses = denDao.retrieveResponsesByPost(denName, postId);
        return responses;
    }



    //MAY ALSO NEED TO LOOK AT PATHVARIABLE ANNOTATIONS BUT UNSURE WHAT THEY WOULD BE NEEDED FOR
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{den}/{id}/comments")
    public ResponseDto createNewResponse(@RequestBody ResponseDto newResponse){
        return denDao.createNewResponse(newResponse);
    }










}
