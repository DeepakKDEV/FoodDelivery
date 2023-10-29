package com.example.FoodDelivery.Model.AuthenticationToken;

import com.example.FoodDelivery.Model.Admin;
import com.example.FoodDelivery.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Entity
public class AuthenticationToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;


    public AuthenticationToken(User user){
        this.userId=user;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    @OneToOne
    @JoinColumn(name = "fk_UserId_")
    User userId;


    public AuthenticationToken(Admin existadmin) {
        this.admin= existadmin;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
      }
    @OneToOne
    @JoinColumn(name = "fk_admin_")
    Admin admin;
}