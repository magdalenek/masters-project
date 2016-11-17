package com.charitable.charity.crud.utils;


import com.charitable.charity.crud.model.Charity;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class Verifier {

    public VerifiedCharity verifiedCharity;

    @Autowired
    private final MongoTemplate mongoTemplate;

    @Autowired
    public Verifier(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

}
