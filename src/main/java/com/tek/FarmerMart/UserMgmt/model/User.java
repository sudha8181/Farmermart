package com.tek.FarmerMart.UserMgmt.model;

import com.tek.FarmerMart.UserMgmt.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "UserCollection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
//    @NotNull      # both are from Validation dependency
//    @Email
    private String email;
    private String alternateEmail;
    private String password;
    private String confirmPassword;
    private String phoneNo;
    private String alternatePhoneNo;
    private Boolean active;
    private Role role=Role.USER;
    private List<Address> addresses;
    private List<BankAccount> bankAccounts;

    @DBRef(lazy = true)
    private List<OrderSummary> orderHistory;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}

