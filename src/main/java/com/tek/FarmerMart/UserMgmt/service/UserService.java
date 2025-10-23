package com.tek.FarmerMart.UserMgmt.service;

import com.tek.FarmerMart.UserMgmt.dto.request.LoginDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserProfileRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserRegistrationRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserVendorDto;
import com.tek.FarmerMart.UserMgmt.dto.response.UserProfileResponseDto;
import com.tek.FarmerMart.UserMgmt.dto.response.UserRegistrationResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {


    public ResponseEntity<UserRegistrationResponseDto> signup(UserRegistrationRequestDto request);

    public ResponseEntity<UserRegistrationResponseDto> login(LoginDto loginDto);

    public ResponseEntity<UserProfileResponseDto> getUser(String user_id);

   public ResponseEntity<List<UserRegistrationResponseDto>> getAllUser();

    public ResponseEntity<String> deactivateUser(String id);

    public ResponseEntity<UserProfileResponseDto> updateProfile(String id, UserProfileRequestDto request);

    public ResponseEntity<String> UserToVendor(UserVendorDto request);
}
