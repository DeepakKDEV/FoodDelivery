package com.example.FoodDelivery.Repository;

import com.example.FoodDelivery.Model.AuthenticationToken.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Integer> {

    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
