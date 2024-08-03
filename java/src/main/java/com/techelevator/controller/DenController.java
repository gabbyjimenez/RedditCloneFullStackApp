package com.techelevator.controller;


import com.techelevator.dao.DenDao;
import com.techelevator.model.Den;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("")
public class DenController {

    @Autowired
    private DenDao denDao;
    @GetMapping (path = "/dens")
    public List<Den> retrieveAllDens(){
        List<Den> dens = denDao.retrieveAllDens();
        return dens;
    }











}
