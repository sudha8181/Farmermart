package com.tek.FarmerMart.UserMgmt.dto.response;

import com.tek.FarmerMart.UserMgmt.model.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserRegistrationResponseDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private Boolean active;
//    private LocalDateTime createdAt;
}
