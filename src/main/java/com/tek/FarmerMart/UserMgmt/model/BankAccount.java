package com.tek.FarmerMart.UserMgmt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class BankAccount {

    @Id
    private String accountId;
    private String accountHolderName;

    @Indexed(unique = true)
    private String accountNumber;
    private String ifscCode;
    private String bankName;
    private String branch;
    private boolean primary;
    private String accountType;

}

