package com.charitable.charity.crud.controller;


import com.charitable.charity.crud.model.Admin;
import com.charitable.charity.crud.model.Charity;
import com.charitable.charity.crud.request.LoginRequest;
import com.charitable.charity.crud.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MongoTemplate mongoTemplate;
    private final TokenService tokenService;

    @Autowired
    public AdminController(MongoTemplate mongoTemplate, TokenService tokenService) {
        this.mongoTemplate = mongoTemplate;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAdmin(@RequestBody LoginRequest loginRequest){
        Query findingCharityAdmin = new Query(Criteria.where("username").is(loginRequest.getUsername()).andOperator(Criteria.where("password").is(loginRequest.getPassword())));

        Charity charity = mongoTemplate.findOne(findingCharityAdmin, Charity.class);

        if (charity == null) {
            throw new NotFoundException();
        }

        return tokenService.generateTokenForCharity(charity.getUsername());
    }


    @RequestMapping("/main")
    public String serveAdmin(@RequestParam("id") String id, ModelMap model) {

        Query findingCharity = new Query(Criteria.where("id").is(id));
        Charity charity = mongoTemplate.findOne(findingCharity, Charity.class);
        model.put("charity", charity);

        return "admin/charity-admin";
    }

    @RequestMapping("/preview")
    public String servePage(@RequestParam("id") String id, ModelMap model) {

        Query findingCharity = new Query(Criteria.where("id").is(id));
        Charity charity = mongoTemplate.findOne(findingCharity, Charity.class);
        model.put("charity", charity);

        return "admin/charity-preview";
    }

    @RequestMapping("/update")
    public String updateCharity(@RequestParam("id") String id, ModelMap model) {

        Query findingCharity = new Query(Criteria.where("id").is(id));
        Charity charity = mongoTemplate.findOne(findingCharity, Charity.class);
        model.put("charity", charity);

        return "admin/charity-update";
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

}