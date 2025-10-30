package com.tek.FarmerMart.UserMgmt.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String alternateEmail;
    private String password;
    private  String confirmPassword;
    private String phoneNo;
    private String alternatePhoneNo;
//    private LocalDateTime createdAt;
}
