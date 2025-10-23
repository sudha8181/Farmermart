package com.tek.FarmerMart.UserMgmt.exception.vendor;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneNumberException extends RuntimeException {

    private String msg;



}
