package com.techelevator.controller;


import com.techelevator.dao.DenDao;
import com.techelevator.model.CategoryDTO;
import com.techelevator.model.DenDto;
import com.techelevator.model.PostDto;
import com.techelevator.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
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

    @GetMapping (path = "/{den}/posts")
    public List<PostDto> retrievePostsByDenName(@PathVariable("den") String denName){
        List<PostDto> posts = denDao.retrievePostsByDenName(denName);
        return posts;
    }

    @GetMapping(path = "/categories")
    public List<CategoryDTO> retrieveAllCategories() {
        return denDao.retrieveAllCategories();

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


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{den}")
    public void deleteDenByDenName(@PathVariable("den") String denName){

        denDao.deleteDenByDenName(denName);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{den}/{id}")
    public void deletePostByPostId(@PathVariable("den") String denName, @PathVariable ("id") int postId){

        denDao.deletePostByPostId(postId);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{den}/{id}/comments/{commentId}")
    public void deleteCommentByCommentId(@PathVariable("den") String denName, @PathVariable ("id") int postId, @PathVariable ("commentId") int commentId){

        denDao.deleteCommentByCommentId(commentId);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/{den}/{id}/pin")
    public PostDto togglePinnedPost(@PathVariable("den") String denName, @PathVariable("id") int postId, @RequestBody PostDto postToPin){
        return denDao.pinPost(postToPin);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/{den}/{id}/comments/{commentId}/pin")
    public ResponseDto togglePinnedComment(@PathVariable("den") String denName, @PathVariable("id") int postId, @PathVariable("commentId") int responseId, @RequestBody ResponseDto responseToPin){
        return denDao.pinResponse(responseToPin);
    }

    @GetMapping(path="/{userId}/favorites")
    public List<DenDto> favoritesList (@PathVariable("userId") int userId){

        
        return denDao.getFavoritesByUserId(userId);
    }


}
