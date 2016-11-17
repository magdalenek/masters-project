package com.charitable.charity.crud.controller;

import com.charitable.charity.crud.model.Admin;
import com.charitable.charity.crud.request.LoginRequest;
import com.charitable.charity.crud.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/general")
public class ServiceAdminController {

    private final MongoTemplate mongoTemplate;
    private final TokenService tokenService;

    @Autowired
    public ServiceAdminController(MongoTemplate mongoTemplate, TokenService tokenService) {
        this.mongoTemplate = mongoTemplate;
        this.tokenService = tokenService;
    }

    //Create admin
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> createAdmin(@RequestBody Admin admin) throws UnknownHostException {

        Map<String, Object> response = new LinkedHashMap<>();
        mongoTemplate.save(admin);
        response.put("message", "Admin created successfully");
        return response;
    }

    //Get admin by username
    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public Admin getAdminDetails(@PathVariable("username") String username) {

        Query findingAdmin = new Query(Criteria.where("username").is(username));

        Admin admin = mongoTemplate.findOne(findingAdmin, Admin.class);

        if (admin == null) {
            throw new NotFoundException();
        }

        return admin;

    }

    //Update admin
    @RequestMapping(method = RequestMethod.PUT, value = "/{username}")
    public Map<String, Object> editCharity(@PathVariable("username") String username,
                                           @RequestBody Admin admin) {
        admin.setUsername(username);
        mongoTemplate.save(admin);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Details updated successfully");
        return response;
    }

    //Get All Admins
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Map<String, Object> getAllAdmins() {
        List<Admin> allAdmins = mongoTemplate.findAll(Admin.class);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("totalAdmins", allAdmins.size());
        response.put("admins", allAdmins);
        return response;
    }

    //Login
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginAdmin(@RequestBody LoginRequest loginRequest){

        Query findingAdmin = new Query(Criteria.where("username").is(loginRequest.getUsername())
                .andOperator(Criteria.where("password").is(loginRequest.getPassword())));
        Admin admin = mongoTemplate.findOne(findingAdmin, Admin.class);
        System.out.println(admin.toString());

        if (admin == null) {
            throw new NotFoundException();
        }

        return tokenService.generateTokenForAdmin(admin.getUsername());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }


}
