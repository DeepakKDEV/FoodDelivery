package com.example.FoodDelivery.Controller;

import com.example.FoodDelivery.Model.Admin;
import com.example.FoodDelivery.Model.FoodItem;
import com.example.FoodDelivery.Model.Order;
import com.example.FoodDelivery.Model.User;
import com.example.FoodDelivery.Model.singIndto.SingInInput;
import com.example.FoodDelivery.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class AdminController {

      @Autowired
      AdminService adminService;


      @PostMapping("Admin/signUp")
      public String adminSignUp(@RequestBody   @Valid Admin newAdmin)
      {
            return adminService.adminSignUp(newAdmin);
      }

      @PostMapping("signIn/Admin")
      public String AdminSignIn(@Valid @RequestBody SingInInput admin)
      {
            return adminService.AdminSignIn(admin);
      }

      @PostMapping("add/food")
      public  String  foodItems(@RequestBody FoodItem  newFood, @RequestParam String email,@RequestParam String tokenValue){
            return  adminService.foodItems(newFood,email,tokenValue);
      }

      @GetMapping("get/Foods")
      public List<FoodItem> getAllFoods( @RequestParam String email,@RequestParam String tokenValue){
            return adminService.getAllFoods(email,tokenValue);
      }
      @GetMapping("get/order")
      public List<Order> getAllOrders(@RequestParam String email , @RequestParam String tokenValue){
            return adminService.getAllOrders(email,tokenValue);
      }

      @PutMapping("update/order/id/{id}")
      public  String  updateOrder(@PathVariable Integer id, @RequestBody FoodItem foodItem, @RequestParam String email , @RequestParam String tokenValue){
            return  adminService. updateOrder(id, foodItem, email,tokenValue);
      }

      @DeleteMapping("Delete/by/id/{id}")
      public String  deleteFood(@PathVariable Integer id, @RequestParam String email, @RequestParam String tokenValue){
            return  adminService.deleteFood(id, email,tokenValue);
      }


}
