package com.example.FoodDelivery.Service.AuthenticationService;

import com.example.FoodDelivery.Model.AuthenticationToken.AuthenticationToken;
import com.example.FoodDelivery.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    public  boolean authenticationAd(String email, String tokenValue) {
        AuthenticationToken token =  iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getAdmin().getEmail().equals(email);
        }else
        {
            return false;
        }
    }

    public void CreationToken(AuthenticationToken token) {
        iAuthenticationRepo.save(token);
    }

    public  boolean authenticate(String email, String tokenValue) {
        AuthenticationToken token =  iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUserId().getEmail().equals(email);
        }else
        {
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AuthenticationToken token =  iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        iAuthenticationRepo.delete(token);
    }

}