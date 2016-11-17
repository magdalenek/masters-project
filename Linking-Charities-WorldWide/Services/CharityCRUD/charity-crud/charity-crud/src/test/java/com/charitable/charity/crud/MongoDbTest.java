package com.charitable.charity.crud;

import com.charitable.charity.crud.model.Charity;
import com.charitable.charity.crud.utils.VerifiedCharity;
import com.mongodb.MongoClient;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Pattern;


@Ignore
public class MongoDbTest {


    @Test
    public void insert_into_mongo() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);


        String id = "57895f4630045f31994dedc7";

        Charity charity = new Charity();
        charity.setId(id);
        charity.setCharityAddress("Poland");


        template.save(charity);

        Query query = new Query(new Criteria().where("id").is(charity.getId()));
        Charity fromMongo = template.findOne(query, Charity.class);

        List<Charity> all = template.findAll(Charity.class);
        for (Charity charity1 : all) {
            System.out.println(charity1);
        }

    }


    @Test
    public void verify_incorrect_in_mongo() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);

        Charity charity2 = new Charity();
        charity2.setRegistrationNumber("12345");
        charity2.setCharityName("Poland");

        VerifiedCharity verifiedCharity = new VerifiedCharity("2345", "Koko");

        if(verifiedCharity.getCharityName().equals(charity2.getCharityName()) && verifiedCharity.getRegistrationId().equals(charity2.getRegistrationNumber())){
            System.out.println("Verify incorrect method:  matches (should be not matches)");
        } else {
            System.out.println("Verify incorrect method:   does NOT match (should be not matches)");
        }

    }

    @Test
    public void verify_correct_in_mongo() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);

        Charity charityCorrect = new Charity();
        charityCorrect.setRegistrationNumber("12345");
        charityCorrect.setCharityName("Puchatoldia");

        VerifiedCharity verifiedCharity = new VerifiedCharity("12345", "Puchatoldia");

        if (verifiedCharity.getCharityName().equals(charityCorrect.getCharityName()) && verifiedCharity.getRegistrationId().equals(charityCorrect.getRegistrationNumber())) {
            System.out.println("Verify correct:   matches  (should be matches)");
        } else {
            System.out.println("Verify correct:   does NOT match (should be matches) ");
        }

    }


    @Test
    public void check_verified() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);

        Charity charity2 = new Charity();
        charity2.setRegistrationNumber("1037651");
        charity2.setCharityName("Puchatoldia");

        Query findingCharity = new Query(Criteria.where("_id").is(charity2.getRegistrationNumber()));

        VerifiedCharity verifiedCharity = template.findOne(findingCharity, VerifiedCharity.class);

        if(verifiedCharity.getRegistrationId().equals(charity2.getRegistrationNumber())){
            System.out.println("Verify incorrect method:  matches (should match)");
            System.out.print("Name for this id: " + verifiedCharity.getCharityName());
        } else {
            System.out.println("Verify incorrect method:   does NOT match (should match)");
        }

    }

}
