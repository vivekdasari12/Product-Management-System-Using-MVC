package com.sathya.productmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;



@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                

    @Column(nullable = false, length = 100)
    private String name;            

    private Double price;           

    private String brand;           

    private Integer quantity;       

    private String imageurl;           

    private Double discountPrice;   

    private Double totalPrice;      
    private String owner;           

    private LocalDateTime createdAt; 
    
    @PrePersist
    public void prePersist() {
        this.owner = System.getProperty("user.name"); 
        this.createdAt = LocalDateTime.now();         
    }
}
