package com.tek.FarmerMart.UserMgmt.exception.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private String msg;



}
