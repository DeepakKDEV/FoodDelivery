package com.example.FoodDelivery.Model;

import com.example.FoodDelivery.Model.enumEntity.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private   Integer userId;
        @NotNull
        private   String  userName;

        @Enumerated(value = EnumType.STRING)
        private   Gender gender;
        @NotNull
        private   String  password;

        @Size(min = 10, max = 12)
        private   String  phoneNumber;

        @NotBlank
        @Email
        private   String   email;

        @NotNull
        private   String  address;


}
