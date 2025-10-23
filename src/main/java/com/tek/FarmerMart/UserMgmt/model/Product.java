package com.tek.FarmerMart.UserMgmt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductCollection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String productID;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String category;
    private String subCategory;
}
