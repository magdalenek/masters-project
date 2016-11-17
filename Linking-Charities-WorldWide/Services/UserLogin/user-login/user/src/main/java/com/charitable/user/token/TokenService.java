package com.charitable.user.token;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenService {

    private SecureRandom secureRandom = new SecureRandom();

    private Map<String, String> userCache = new ConcurrentHashMap<>();


    public String generateTokenForUser(String id) {
        String token = generateToken();
        System.out.println("adding token for user " + id + " token: " + token);
        userCache.put(token, id);
        return  token;
    }

    public String getUserIdFromToken(String token){
        return userCache.get(token);
    }

    private  String generateToken(){
        return new BigInteger(130, secureRandom).toString(32);
    }


    public static void main(String[] args) {
        TokenService ts = new TokenService();

        String puchateksToken = ts.generateTokenForUser("Test Testing");
        System.out.println(puchateksToken);
        System.out.println(ts.getUserIdFromToken(puchateksToken));
    }


}
