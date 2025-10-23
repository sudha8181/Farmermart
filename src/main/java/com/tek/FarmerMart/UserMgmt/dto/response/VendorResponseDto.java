package com.tek.FarmerMart.UserMgmt.dto.response;

import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import com.tek.FarmerMart.UserMgmt.model.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VendorResponseDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String linkedUserId;
    private String aadhaarNumber;
    private Address farmAddress;
    private String produceType;
    private List<BankAccount> bankAccounts;
    private boolean verified;
    private List<Product> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
