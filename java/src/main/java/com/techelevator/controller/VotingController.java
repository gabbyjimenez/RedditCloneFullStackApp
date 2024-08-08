package com.techelevator.controller;


import com.techelevator.dao.VotingDao;
import com.techelevator.model.ResponseDto;
import com.techelevator.model.VotingDto;
import com.techelevator.service.IVotingService;
import com.techelevator.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping(path = "/{den}/{id}/comments/{responseId}/upvote")
    public VotingDto addUpvoteToResponse(@RequestBody ResponseDto responseToUpvote, Principal principal, @PathVariable("den") String denName, @PathVariable("id") int postId, @PathVariable("responseId") int responseId){



        return votingService.getVoteStatusByObject(responseToUpvote, principal);



    }




































}
