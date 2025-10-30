package com.tek.FarmerMart.UserMgmt.controller;

import com.tek.FarmerMart.UserMgmt.dto.request.LoginDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserProfileRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserRegistrationRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserVendorDto;
import com.tek.FarmerMart.UserMgmt.dto.response.UserProfileResponseDto;
import com.tek.FarmerMart.UserMgmt.dto.response.UserRegistrationResponseDto;
import com.tek.FarmerMart.UserMgmt.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserServiceImp userService;


    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponseDto> signup(@RequestBody UserRegistrationRequestDto user){
      return  userService.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegistrationResponseDto> login(@RequestBody LoginDto login){
        return  userService.login(login);
    }

    // ADMIN - Get all users
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserRegistrationResponseDto>> getAllUsers(){
        return userService.getAllUser();
    }

    //ADMIN- Deactive a user/vendor
    @PutMapping("/deactivate/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deactivateUser(@PathVariable String id){
        return userService.deactivateUser(id);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('USER','VENDOR','ADMIN')")
    public ResponseEntity<UserProfileResponseDto> getUserById(@PathVariable("id") String id){
        return userService.getUser(id);
    }


    @PutMapping("/update/profile")
    public ResponseEntity<UserProfileResponseDto> updateProfile(@RequestParam String id, @RequestBody UserProfileRequestDto request)
    {
        return userService.updateProfile(id,request);
    }
    @PostMapping("/vendor")
    public ResponseEntity<String> convertIntoVendor(@RequestBody UserVendorDto request){
        return userService.UserToVendor(request);

    }







}
