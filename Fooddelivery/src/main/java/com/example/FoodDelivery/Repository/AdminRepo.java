package com.example.FoodDelivery.Repository;

import com.example.FoodDelivery.Model.Admin;
import com.example.FoodDelivery.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin findFirstByEmail(String newEmail);
}
