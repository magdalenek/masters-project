package com.charitable.charity.crud.utils;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "VerifiedCharity")
public class VerifiedCharity {

    @Id
    private final String registrationId;

    private final String charityName;


    public VerifiedCharity(String registrationId, String charityName) {
        this.registrationId = registrationId;
        this.charityName = charityName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getCharityName() {
        return charityName;
    }


}
