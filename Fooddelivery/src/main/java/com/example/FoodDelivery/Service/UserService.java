package com.example.FoodDelivery.Service;

import com.example.FoodDelivery.Model.AuthenticationToken.AuthenticationToken;
import com.example.FoodDelivery.Model.Order;
import com.example.FoodDelivery.Model.User;
import com.example.FoodDelivery.Model.singIndto.SingInInput;
import com.example.FoodDelivery.Repository.IAuthenticationRepo;
import com.example.FoodDelivery.Repository.IOrderRepo;
import com.example.FoodDelivery.Repository.IUserRepo;
import com.example.FoodDelivery.Service.AuthenticationService.AuthenticationTokenService;
import com.example.FoodDelivery.Service.PasswordEncryptor.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

       @Autowired
      IUserRepo  iUserRepo;
       @Autowired
      IOrderRepo iOrderRepo;

       @Autowired
       OrderService orderService;


       @Autowired
       IAuthenticationRepo iAuthenticationRepo;
       @Autowired
       AuthenticationTokenService authenticationTokenService;

    public String userSignUp(User newUser) {

        String newEmail = newUser.getEmail();

        User existUser =iUserRepo.findFirstByEmail(newEmail);

        if (existUser != null) {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getPassword();

        try {
            String encryptedPassword = PasswordEncrypt.encrypt(signUpPassword);

            newUser.setPassword(encryptedPassword);

             iUserRepo.save(newUser);
            return "User registered Successfully";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    //      SIGN IN User  //
    public String userSignIn(SingInInput user) {
        String email = user.getEmail();

        User existingUser = iUserRepo.findFirstByEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }
        String password = user.getPassword();

        try {
            String encryptedPassword = PasswordEncrypt.encrypt(password);

            if(existingUser.getPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existingUser);

                   authenticationTokenService.CreationToken(token);
                    return token.getTokenValue();
            }
            else {
                return "Invalid Credentials!!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }
    }


    //    SIGN OUT ///
       public String userSignOut(String email, String tokenValue) {
        if(authenticationTokenService.authenticate(email, tokenValue)) {
            authenticationTokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
         }
         else {
            return "Un Authenticated access!!!";
        }
    }


    public String OrderByUser(Order newOrder, String email, String tokenValue) {
               if(authenticationTokenService.authenticate(email,tokenValue)){
                   iOrderRepo.save(newOrder);
                   return  "new order Added done";
               }
             return "token is  not valid ";
    }

}
