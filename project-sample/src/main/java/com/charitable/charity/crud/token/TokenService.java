package com.charitable.charity.crud.token;

import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {

    private SecureRandom secureRandom = new SecureRandom();

    private Map<String, String> adminCache = new ConcurrentHashMap<>();
    private Map<String, String> charityCache = new ConcurrentHashMap<>();


    public String generateTokenForAdmin(String adminId) {
        String token = generateToken();
        System.out.println("adding token for admin user " + adminId + " token: " + token);
        adminCache.put(token, adminId);
        return  token;
    }

    public String getAdminIdFromToken(String token){
        return adminCache.get(token);
    }

    public String generateTokenForCharity(String charityId) {
        String token = generateToken();
        System.out.println("adding token for charity " + charityId + " token: " + token);
        charityCache.put(token, charityId);
        return  token;
    }

    public String getCharityIdFromToken(String token){
        return charityCache.get(token);
    }


    private  String generateToken(){
        return new BigInteger(130, secureRandom).toString(32);
    }


    public static void main(String[] args) {
        TokenService ts = new TokenService();

        String puchateksToken = ts.generateTokenForAdmin("puchatek");
        System.out.println(puchateksToken);
        System.out.println(ts.getAdminIdFromToken(puchateksToken));
    }


}
