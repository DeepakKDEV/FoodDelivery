package com.example.FoodDelivery.Service;

import com.example.FoodDelivery.Model.FoodItem;
import com.example.FoodDelivery.Repository.AdminRepo;
import com.example.FoodDelivery.Repository.IFoodItemRepo;
import com.example.FoodDelivery.Service.AuthenticationService.AuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {


    @Autowired
    IFoodItemRepo iFoodItemRepo;
    @Autowired
    AuthenticationTokenService authenticationTokenService;


    public String FoodItemAdd(FoodItem newFood) {
        iFoodItemRepo.save(newFood);
        return "save food";
    }

    public String updateItem(Integer id, String fItems) {

        FoodItem items = iFoodItemRepo.findById(id).orElseThrow();
        items.setName(fItems);
        iFoodItemRepo.save(items);
        return " updated successfully";
    }

    public String foodItems(FoodItem newFood, String email, String tokenValue) {
        if(authenticationTokenService.authenticationAd(email,tokenValue)){
        //    newFood.setAdmin(adminRepo.findFirstByEmail(email));
         iFoodItemRepo.save(newFood);
            return  "new food items Added ";
        }
        return "token is  not valid ";

    }


    public List<FoodItem> getAllFoods(String email, String tokenValue) {
             if(authenticationTokenService.authenticationAd(email,tokenValue)){
                return iFoodItemRepo.findAll();
             }

        return null;
    }

    public String updateOrder(Integer id,FoodItem foodItem, String email, String tokenValue) {
        if (authenticationTokenService.authenticationAd(email,tokenValue)){
            FoodItem foodItem1 = iFoodItemRepo.findById(id).orElseThrow();
            foodItem1.setPrice(foodItem1.getPrice());
            iFoodItemRepo.save(foodItem1);
            return "foodItem updated";
        }
        return email;
    }

    public String deleteFood(Integer id, String email, String tokenValue) {
        if(authenticationTokenService.authenticationAd(email,tokenValue)){
            iFoodItemRepo.deleteById(id);
            return "foodItem deleted";
        }

        return null;
    }
}
