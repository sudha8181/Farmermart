package com.tek.FarmerMart.UserMgmt.model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String type; // House, work, farm, any other address

}

