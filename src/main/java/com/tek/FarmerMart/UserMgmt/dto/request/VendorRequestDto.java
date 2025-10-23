package com.tek.FarmerMart.UserMgmt.dto.request;


import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import com.tek.FarmerMart.UserMgmt.enums.ProduceType;
import com.tek.FarmerMart.UserMgmt.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class VendorRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String linkedUserId;
    private String aadhaarNumber;
    private Address farmAddress;
    private ProduceType produceType;
    private List<BankAccount> bankAccounts;
    private List<Product> products;


//    private boolean verified;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;


}

