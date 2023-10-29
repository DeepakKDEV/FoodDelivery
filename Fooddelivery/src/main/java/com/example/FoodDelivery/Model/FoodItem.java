package com.example.FoodDelivery.Model;

import com.example.FoodDelivery.Model.enumEntity.TypeFood;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @Enumerated(value = EnumType.STRING)
    private TypeFood category; // Category to which the food item belongs (e.g., fruits, vegetables, meats)

    private double price; // Price of the food item
    private Integer FoodQuantity; // Quantity of the food item in stock

    @ManyToOne
    Admin admin;

}
