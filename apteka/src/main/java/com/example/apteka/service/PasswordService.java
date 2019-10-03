package com.example.apteka.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordService {

    public String codePassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1,messageDigest);
            return no.toString();
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
