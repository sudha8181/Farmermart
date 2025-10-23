package com.tek.FarmerMart.UserMgmt.dto.response;

import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import com.tek.FarmerMart.UserMgmt.model.OrderSummary;
import com.tek.FarmerMart.UserMgmt.enums.Role;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserProfileResponseDto {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String alternateEmail;
    private String phoneNo;
    private String alternatePhoneNo;
    private Boolean active;
    private Role role;
    private List<Address> addresses;
    private List<BankAccount> bankAccounts;
    private List<OrderSummary> orderHistory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
