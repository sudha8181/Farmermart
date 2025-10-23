package com.tek.FarmerMart.UserMgmt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_summaries")
public class CompanyInfo {


    @Id
    private String companyId;
    private String companyName;

    @Indexed(unique = true)
    private String registrationNumber;
    private String gstNumber;
    private String website;
    private String address;
    private String contactPerson;
}
