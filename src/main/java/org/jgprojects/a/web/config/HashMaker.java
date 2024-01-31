package org.jgprojects.a.web.config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashMaker {
    public String hash(String text) throws NoSuchAlgorithmException{
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] hashedPassword = md.digest(text.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPassword);
    }
    public boolean comparator(String hash, String noHash){
        try{
            return (this.hash(noHash).equals(hash));
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
