package com.avinash.ProjectDEMO.Security_Login;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    long EXTIRPATION = 1000*60*4;
    String secret = "VENOMTECHNOLOGIES7sdfghjksdfghjkdfghjwertyuiSDFGHJKHJKGHZXCVBASDFGHJERTYUDFGHJSDFGHWERTYSDFGHJHYHTF";


    public String GenerateToken(String email){
        String encryptedToken = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXTIRPATION))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return encryptedToken;
    }

    public boolean validToken(String encryptedToken) throws ExpiredJwtException {
        boolean flag = true;
        try{
            Date expirationDate=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(encryptedToken)
                    .getBody()
                    .getExpiration();
            Date currentDate=java.util.Calendar.getInstance().getTime();
            flag = true;
        }
        catch(ExpiredJwtException e){
            flag = false;
            throw e;}
        finally{return flag;}

    }
    public String getTokenDetails(String encryptedToken){
        String email = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(encryptedToken)
                .getBody()
                .getSubject();
        return email;
    }
}

