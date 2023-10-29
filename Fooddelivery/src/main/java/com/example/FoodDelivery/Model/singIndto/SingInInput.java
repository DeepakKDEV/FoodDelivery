package com.example.FoodDelivery.Model.singIndto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class SingInInput {

    private   String email;
    private String  password;


}
