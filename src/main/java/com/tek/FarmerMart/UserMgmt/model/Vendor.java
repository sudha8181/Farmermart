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

@Document(collection = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    @Id
    private String vendorId;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String email;

    //    private String alternateEmail;   # This is Field is currently not Required
    private String password;
    private String phone;

    private Role role = Role.VENDOR;

    private String linkedUserId;
    private String aadhaarNumber;
    private Address farmAddress;
    private String produceType;
    private List<BankAccount> bankAccounts;
    private boolean verified;

    @DBRef
    private List<Product> products;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}

