package com.tek.FarmerMart.UserMgmt.dto.request;

import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserProfileRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String alternateEmail;
    private String password;
    private String phoneNo;
    private String alternatePhoneNo;
    private List<Address> addresses;
    private List<BankAccount> bankList;
    private LocalDateTime updatedAt;

}
