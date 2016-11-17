package com.charitable.charity.crud;

import com.charitable.charity.crud.model.Charity;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class HitEndpointTest {

    private static RestTemplate restTemplate = new RestTemplate();
    private static  final String URL = "http://localhost:9051/";


    /*
    @Test
    public void simple_test(){
        Charity charity = new Charity("charityname", "contactName", "www.www.www", "999", "addr", "short-desc", "desc", "123", "456", "678", "90",);
        addCharity(charity);
    }
    */


    private void addCharity(Charity charity) {
        LinkedMultiValueMap valueMap = new LinkedMultiValueMap();
        valueMap.add("content-type", "application/json");
        ResponseEntity<Map> response = restTemplate.exchange(URL + "charity", HttpMethod.POST, new HttpEntity<Object>(charity, valueMap), Map.class);
        Map<String, Object> map = response.getBody();
        System.out.println();
    }
}
