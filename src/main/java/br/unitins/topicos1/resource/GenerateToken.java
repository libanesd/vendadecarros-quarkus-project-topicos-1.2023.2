package br.unitins.topicos1.resource;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class GenerateToken {
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token =
           Jwt.issuer("unitins-jwt") 
             .upn("jdoe@quarkus.io") 
             .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
             .claim(Claims.birthdate.name(), "2007-07-17") 
           .sign();
        System.out.println(token);
    }
}