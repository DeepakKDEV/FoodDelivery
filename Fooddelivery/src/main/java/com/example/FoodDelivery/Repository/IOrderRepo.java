package com.example.FoodDelivery.Repository;

import com.example.FoodDelivery.Model.Order;

import com.example.FoodDelivery.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepo extends JpaRepository<Order,Integer> {

}
