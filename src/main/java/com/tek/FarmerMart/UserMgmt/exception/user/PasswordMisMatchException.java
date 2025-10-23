package com.tek.FarmerMart.UserMgmt.exception.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordMisMatchException extends RuntimeException {
    private String msg;
}
