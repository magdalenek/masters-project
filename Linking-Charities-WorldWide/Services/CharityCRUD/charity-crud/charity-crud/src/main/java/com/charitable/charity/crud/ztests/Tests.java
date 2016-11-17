package com.charitable.charity.crud.ztests;

import com.charitable.charity.crud.model.Charity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class Tests {

    public static void main(String[] args) {
            Charity charity = new Charity();
            charity.setCharityName("Help poor Puchatki");
            charity.setCharityWebsiteName("www.hpp.org.apoopoo");
            charity.setCharityContactName("Maly Puchatek");
            charity.setCharityTel("00000000000");
            charity.setCharityEmail("helpdesk@hpp.org");
            charity.setRegistrationNumber("101010");
            charity.setCharityAddress("200 Bialystok");
            charity.setCharityShortDescription("End the sadness");
            charity.setCharityDescription("Help end the Polar Bear sadness and donate");
            charity.setEmployeesNo(200);
            charity.setAverageDonations(1337);
            charity.setUsername("qazqaz123");
            charity.setPassword("qazqaz123");

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.exchange("http://localhost:9051/api/charity", HttpMethod.POST, new HttpEntity<Object>(charity), Map.class);

        }
    }
