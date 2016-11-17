package com.charitable.admin.controller;


import models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MongoTemplate mongoTemplate;
    private static final String DOMAIN = "magdalena_s@hotmail.co.uk";

    @Autowired
    public AdminController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    //Register
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody Admin admin) {

        Map<String, Object> response = new LinkedHashMap<>();

        if(admin.getEmail().equals(DOMAIN)) {
            mongoTemplate.save(admin);
            response.put("message", "Admin account created successfully");
        }else {
            response.put("message", "Not authorised to create admin account.");
        }

        return response;
    }


    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }



}
