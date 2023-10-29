package com.example.FoodDelivery.Repository;
import com.example.FoodDelivery.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo  extends JpaRepository<FoodItem,Integer> {

}
