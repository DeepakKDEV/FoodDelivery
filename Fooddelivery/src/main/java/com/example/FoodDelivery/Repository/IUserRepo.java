package com.example.FoodDelivery.Repository;

import com.example.FoodDelivery.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer> {

      User findFirstByEmail(String email);
}
