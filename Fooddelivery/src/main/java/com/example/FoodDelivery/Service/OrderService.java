package com.example.FoodDelivery.Service;

import com.example.FoodDelivery.Model.Order;
import com.example.FoodDelivery.Model.User;
import com.example.FoodDelivery.Repository.IOrderRepo;
import com.example.FoodDelivery.Repository.IUserRepo;
import com.example.FoodDelivery.Service.AuthenticationService.AuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

       @Autowired
       IOrderRepo iOrderRepo;

       @Autowired
       IUserRepo iUserRepo;



       @Autowired
       AuthenticationTokenService authenticationTokenService;

       public List<Order> getAllOrders(String email, String tokenValue) {
              if(authenticationTokenService.authenticationAd(email,tokenValue)){
                     return iOrderRepo.findAll();
              }
           return null;
       }


       public List<Order> getOrder(String  email, String tokenValue) {
              if(authenticationTokenService.authenticate(email,tokenValue)){
                     return   iOrderRepo.findAll();
              }

           return null;
       }
}
