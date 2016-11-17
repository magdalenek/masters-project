package com.charitable.charity.crud.controller;

import com.charitable.charity.crud.mail.EmailSender;
import com.charitable.charity.crud.model.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/authenticate/")
public class TokenController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TokenController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{token}")
    public String getToken(@PathVariable("token") String token, ModelMap model) {

        Query findAndVerify = new Query(Criteria.where("verified").ne(Boolean.TRUE).and("token").is(token));

        Charity charityToVerify = mongoTemplate.findOne(findAndVerify, Charity.class);

        if (charityToVerify == null) {
            return "token/token-not-correct";

        } else {
            charityToVerify.setVerifiedEmail(true);
            mongoTemplate.save(charityToVerify);

            EmailSender emailVerification = new EmailSender();

            if (charityToVerify.isVerifiedEmail() && charityToVerify.isVerifiedData()) {
                charityToVerify.setVerified(true);
                emailVerification.sendWelcomeMessage(charityToVerify);
            } else {
                charityToVerify.setVerified(false);
                emailVerification.sendRejectionEmail(charityToVerify);
            }

            mongoTemplate.save(charityToVerify);

            return "token/token-validation";
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

}
