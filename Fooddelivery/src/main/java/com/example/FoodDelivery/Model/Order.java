package com.example.FoodDelivery.Model;

import com.example.FoodDelivery.Model.enumEntity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name="FoodOrder")
public class Order {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private  Integer OrderId;
       @Enumerated(value = EnumType.STRING)
       private  Status   status;
       private LocalDateTime localDateTime;



       @ManyToMany
       List<FoodItem> foodItem;

       @ManyToOne()
       @JoinColumn(name = "FK_userId")
       User userId;


}
