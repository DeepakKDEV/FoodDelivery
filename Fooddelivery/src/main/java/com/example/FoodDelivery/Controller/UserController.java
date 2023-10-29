package com.example.FoodDelivery.Controller;

import com.example.FoodDelivery.Model.Order;
import com.example.FoodDelivery.Model.User;
import com.example.FoodDelivery.Model.singIndto.SingInInput;
import com.example.FoodDelivery.Service.OrderService;
import com.example.FoodDelivery.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;


    @PostMapping("user/signUp")
    public String userSignUp(@RequestBody @Valid User newUser) {
        return userService.userSignUp(newUser);
    }

    @PostMapping("signIn/user")
    public String userSignIn(@RequestBody SingInInput user) {
        return userService.userSignIn(user);
    }

    //sign out
    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String tokenVal) {
        return userService.userSignOut(email, tokenVal);
    }

    @PostMapping("Order/by/user")
    public String OrderByUser(@RequestBody @Valid Order newOrder, @RequestParam String email, @RequestParam String tokenValue) {

        return userService.OrderByUser(newOrder, email, tokenValue);
    }
    @GetMapping("user/order")
    public List<Order> getOrder( @RequestParam String email, @RequestParam String tokenValue){
        return orderService.getOrder(email,tokenValue);
    }



}
