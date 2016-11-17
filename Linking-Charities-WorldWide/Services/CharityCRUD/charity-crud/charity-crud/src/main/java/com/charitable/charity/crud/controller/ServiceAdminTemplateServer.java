package com.charitable.charity.crud.controller;

import com.charitable.charity.crud.model.Charity;
import com.charitable.charity.crud.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/general")
public class ServiceAdminTemplateServer {

    @Autowired
    private final MongoTemplate mongoTemplate;

    @Autowired
    private final TokenService tokenService;

    @Autowired
    public ServiceAdminTemplateServer(MongoTemplate mongoTemplate, TokenService tokenService) {
        this.mongoTemplate = mongoTemplate;
        this.tokenService = tokenService;
    }


    @RequestMapping("/account")
    public String serveAdmin(ModelMap model,
                             @RequestHeader(value = "token", required = false)
                             String token,
                             @RequestParam(value = "token", required = false )
                             String alternativeToken
                             ) {

        if(alternativeToken != null) {
            token = alternativeToken;
        }

        String adminName = tokenService.getAdminIdFromToken(token);
        if(adminName == null){
            return "unauthorised";
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("verified").is(Boolean.TRUE));

        List<Charity> featuredCharities = findFeatured();
        List<Charity> verifiedCharities = mongoTemplate.find(query, Charity.class);
        List<Charity> allCharities = mongoTemplate.findAll(Charity.class);

        model.addAttribute("featured", featuredCharities);
        model.addAttribute("total", allCharities.size());
        model.addAttribute("verifiedChar", verifiedCharities.size());
        model.addAttribute("notVerifiedChar", allCharities.size() - verifiedCharities.size());

        return "admin-noncharity/admin-noncharity";
    }


    @RequestMapping("/account/findNotVerified")
    public String findNotVerified(ModelMap model) {

        //String adminName = tokenService.getAdminIdFromToken(token);
        //if(adminName == null){
        //    return "unauthorised";
        //}

        Query query = new Query();
        query.addCriteria(Criteria.where("verified").ne(Boolean.TRUE)).with(new Sort(Sort.Direction.DESC, "_id"));

        List<Charity> flaggedCharities = mongoTemplate.find(query, Charity.class);

        model.addAttribute("flaggedCharities", flaggedCharities);
        model.addAttribute("total", flaggedCharities.size());

        return "admin-noncharity/admin-noncharity-nonverified";
    }


    @RequestMapping("/account/findAll")
    public String findAll(ModelMap model) {

       // String adminName = tokenService.getAdminIdFromToken(token);
        //if(adminName == null){
         //   return "unauthorised";
        //}

        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "_id"));

        List<Charity> allCharities = mongoTemplate.find(query, Charity.class);

        model.addAttribute("allCharities", allCharities);
        model.addAttribute("total", allCharities.size());

        return "admin-noncharity/admin-noncharity-all";
    }


    //Featured Charities
    public List<Charity> findFeatured() {

        Query query = new Query();
        query.limit(4);
        query.with(new Sort(Sort.Direction.DESC, "_id"));

        List<Charity> charities = mongoTemplate.find(query, Charity.class);

        return charities;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

}