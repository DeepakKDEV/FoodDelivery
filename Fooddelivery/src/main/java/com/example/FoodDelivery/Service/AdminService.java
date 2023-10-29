package com.example.FoodDelivery.Service;

import com.example.FoodDelivery.Model.Admin;
import com.example.FoodDelivery.Model.AuthenticationToken.AuthenticationToken;
import com.example.FoodDelivery.Model.FoodItem;

import com.example.FoodDelivery.Model.Order;
import com.example.FoodDelivery.Model.User;
import com.example.FoodDelivery.Model.singIndto.SingInInput;
import com.example.FoodDelivery.Repository.AdminRepo;

import com.example.FoodDelivery.Service.AuthenticationService.AuthenticationTokenService;
import com.example.FoodDelivery.Service.PasswordEncryptor.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminService {

       @Autowired
      AdminRepo    adminRepo;

      @Autowired
      FoodItemService foodItemService;

      @Autowired
      AuthenticationTokenService authenticationTokenService;

      @Autowired
      OrderService orderService;



    public String adminSignUp(Admin newAdmin) {
        String newEmail = newAdmin.getEmail();

        Admin existAdmin =adminRepo.findFirstByEmail(newEmail);

        if (existAdmin != null) {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newAdmin.getPassword();

        try {
            String encryptedPassword = PasswordEncrypt.encrypt(signUpPassword);

            newAdmin.setPassword(encryptedPassword);

          adminRepo.save(newAdmin);
            return "Admin registered Successfully";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }


    public String AdminSignIn(SingInInput admin) {
        String email = admin.getEmail();

        Admin existadmin = adminRepo.findFirstByEmail(email);

        if(existadmin == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }
        String password = admin.getPassword();

        try {
            String encryptedPassword = PasswordEncrypt.encrypt(password);

            if(existadmin.getPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existadmin);

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

    public String foodItems(FoodItem newFood, String email, String tokenValue) {
          return  foodItemService.foodItems(newFood,email,tokenValue);
    }


    public List<FoodItem> getAllFoods(String email, String tokenValue) {
        return  foodItemService.getAllFoods(email,tokenValue);
    }

    public List<Order> getAllOrders(String email, String tokenValue) {
        return  orderService.getAllOrders(email,tokenValue);
    }

    public String updateOrder(Integer id, FoodItem foodItem, String email, String tokenValue) {
           return  foodItemService.updateOrder(id, foodItem, email,tokenValue);
    }

    public String deleteFood(Integer id, String email, String tokenValue) {
        return  foodItemService.deleteFood(id, email,tokenValue);
    }
}
