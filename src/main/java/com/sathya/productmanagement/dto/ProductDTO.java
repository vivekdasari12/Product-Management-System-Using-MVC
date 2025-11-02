package com.sathya.productmanagement.dto;

import lombok.*;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;                 
    private String name;             
    private Double price;            
    private Double discountPrice;    
    private Double totalPrice;       
    private LocalDateTime createdAt; 
    private String owner;            
}
