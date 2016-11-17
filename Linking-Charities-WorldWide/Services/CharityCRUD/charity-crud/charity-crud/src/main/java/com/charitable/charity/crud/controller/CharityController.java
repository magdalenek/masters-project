package com.charitable.charity.crud.controller;

import com.charitable.charity.crud.mail.EmailSender;
import com.charitable.charity.crud.model.Note;
import com.charitable.charity.crud.token.TokenService;
import com.charitable.charity.crud.utils.VerifiedCharity;
import java.net.UnknownHostException;
import java.util.*;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.charitable.charity.crud.model.Charity;

@RestController
@RequestMapping("/api/charity")
public class CharityController {

    private final MongoTemplate mongoTemplate;
    private final TokenService tokenService;

    @Autowired
    public CharityController(MongoTemplate mongoTemplate, TokenService tokenService) {
        this.mongoTemplate = mongoTemplate;
        this.tokenService = tokenService;
    }

    //Create Charity
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> createCharity(@RequestBody Charity charity) throws UnknownHostException {

        Map<String, Object> response = new LinkedHashMap<>();
        UUID val = UUID.randomUUID();
        String tokenVal = val.toString();
        charity.setToken(tokenVal);


        EmailSender emailVerification = new EmailSender();

        new Thread(new Runnable() {
            @Override
            public void run() {
                emailVerification.sendVerificationEmail(charity);
            }
        }).start();

        dataVerifier(charity);

        mongoTemplate.save(charity);

        response.put("message", "Charity page created successfully");
        return response;
    }

    //Get charity by id
    @RequestMapping(method = RequestMethod.GET, value = "/{charityId}")
    public Charity getCharityDetails(@PathVariable("charityId") String id) {

        Query findingCharity = new Query(Criteria.where("id").is(id));

        Charity charity = mongoTemplate.findOne(findingCharity, Charity.class);

        if (charity == null) {
            throw new NotFoundException();
        }

        return charity;

    }

    //Update charity
    @RequestMapping(method = RequestMethod.PUT, value = "/{charityId}")
    public Map<String, Object> editCharity(@PathVariable("charityId") String charityId,
                                           @RequestBody Charity charity) {

        Charity charityInDb = getCharityDetails(charityId);
        charity.setId(charityId);
        charity.setVerified(charityInDb.isVerified());
        charity.setVerifiedEmail(charityInDb.isVerified());
        charity.setVerifiedData(charityInDb.isVerifiedData());
        charity.setToken(charityInDb.getToken());
        charity.setUsername(charityInDb.getUsername());
        charity.setPassword(charityInDb.getPassword());
        charity.setNotes(charityInDb.getNotes());

        mongoTemplate.save(charity);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Details updated successfully");
        return response;
    }


    //Update note
    @RequestMapping(method = RequestMethod.PUT, value = "/notes/{charityId}")
    public Map<String, Object> editNote(@PathVariable("charityId") String charityId,
                                           @RequestBody Note note) {

        String trimmedCharityId = note.getId().substring(0, note.getId().indexOf("&") );
        System.out.println("updating note for charityId " +  trimmedCharityId +  "  with note " + note.getNote());

        Query query = new Query(Criteria.where("id").is(trimmedCharityId));
        Charity charity = mongoTemplate.findOne(query, Charity.class);

        charity.setNotes(note.getNote());
        mongoTemplate.save(charity);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Details updated successfully");
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{charityId}")
    public Map<String, String> deleteCharity(@PathVariable("charityId") String id) {

        Query findingCharity = new Query(Criteria.where("id").is(id));

        mongoTemplate.findAndRemove(findingCharity, Charity.class);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Charity deleted successfully.");
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Map<String, Object> getAllCharities() {
        List<Charity> allCharities = mongoTemplate.findAll(Charity.class);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("totalCharities", allCharities.size());
        response.put("charities", allCharities);


        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

    public void dataVerifier(Charity charity) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("_id").is(charity.getRegistrationNumber()), Criteria.where("charityName").is(charity.getCharityName()));
        query.addCriteria(criteria);

        VerifiedCharity verifiedCharity = template.findOne(query, VerifiedCharity.class);

        if (verifiedCharity != null && verifiedCharity.getCharityName().equalsIgnoreCase(charity.getCharityName()) && verifiedCharity.getRegistrationId().equals(charity.getRegistrationNumber())) {
            System.out.println("VerifiedChar: " + verifiedCharity.toString() + ", charity: " + charity.toString());
            System.out.println("Matches!");
            charity.setVerifiedData(true);
        } else {
            System.out.println("VerifiedChar: " + verifiedCharity.toString() + ", charity: " + charity.toString());
            System.out.println("Does NOT Match!");
            charity.setVerifiedData(false);
        }


    }
}

