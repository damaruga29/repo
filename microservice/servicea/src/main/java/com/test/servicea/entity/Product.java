package com.test.servicea.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "product")
public class Product {

    @Id
    private Long productId;
    private String productName;
    private double price;
}
