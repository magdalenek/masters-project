package com.charitable.admin.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import models.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/general")
public class TemplateController {

    @Autowired
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TemplateController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @RequestMapping("/account")
    public String serveAdmin(ModelMap model) throws IOException {

        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);

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

            Query query = new Query();

            query.addCriteria(Criteria.where("verified").ne(Boolean.TRUE)).with(new Sort(Sort.Direction.DESC, "_id"));

            List<Charity> flaggedCharities = mongoTemplate.find(query, Charity.class);

            model.addAttribute("flaggedCharities", flaggedCharities);
            model.addAttribute("total", flaggedCharities.size());

            return "admin-noncharity/admin-noncharity-nonverified";
        }


        @RequestMapping("/account/findAll")
        public String findAll(ModelMap model) {

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



    }
