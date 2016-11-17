package com.charitable.user.controller;

import com.charitable.user.email.EmailSender;
import com.charitable.user.model.User;
import com.charitable.user.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TokenController {

    MongoOperations mongoOperation;

    @RequestMapping(method = RequestMethod.GET, value = "/{token}")
    public String getToken(@PathVariable("token") String token, ModelMap model) {

        Query findAndVerify = new Query(Criteria.where("token").is(token));

        User user = mongoOperation.findOne(findAndVerify, User.class);

        if ( user == null) { //findBY
            return "token/token-not-correct";

        } else {

            EmailSender emailVerification = new EmailSender();

            if (user.isVerifiedEmail()) {
                user.setVerifiedEmail(true);
                emailVerification.sendWelcomeMessage(user);
            } else {
                user.setVerifiedEmail(false);
                emailVerification.sendRejectionEmail(user);
            }

            mongoOperation.save(user);

            return "token/token-validation";
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

}
