package com.tek.FarmerMart.UserMgmt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "order_summaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSummary {
    @Id
    private String order;
    private String userId;
    private String productId;
    // Reference to User
    private String orderId;            // Actual order in Order Service
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;
    private String deliveryAddressId;
}
