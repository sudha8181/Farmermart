package com.tek.FarmerMart.UserMgmt.service;

import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import com.tek.FarmerMart.UserMgmt.model.Vendor;

import java.util.List;

import com.tek.FarmerMart.UserMgmt.dto.request.VendorRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.response.VendorResponseDto;
import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorService {

    // VENDOR CRUD
    //
    //
    ResponseEntity<VendorResponseDto> createVendor(VendorRequestDto requestDto);ResponseEntity<VendorResponseDto> getVendorById(String id);
    ResponseEntity<VendorResponseDto> getVendorByEmail(String email);
    ResponseEntity<VendorResponseDto> getVendorByPhone(String phone);
    ResponseEntity<VendorResponseDto> getVendorByAadhaar(String aadhaarNumber);
    ResponseEntity<VendorResponseDto> getVendorByLinkedUserId(String linkedUsrId);
    ResponseEntity<List<VendorResponseDto>> getAllVendors();
    ResponseEntity<VendorResponseDto> updateVendor(String id, VendorRequestDto requestDto);
    ResponseEntity<Void> deleteVendor(String id);

    // Verification
    ResponseEntity<List<VendorResponseDto>> getVerifiedVendors();
    ResponseEntity<List<VendorResponseDto>> getUnverifiedVendors();
    ResponseEntity<VendorResponseDto> verifyVendor(String id);
    ResponseEntity<VendorResponseDto> unverifyVendor(String id);

    // Search & Filter
    ResponseEntity<List<VendorResponseDto>> getVendorsByProduceType(String produceType);
    ResponseEntity<List<VendorResponseDto>> searchVendorsByName(String name);

    // Farm Address
    ResponseEntity<VendorResponseDto> updateFarmAddress(String id, Address farmAddress);
    ResponseEntity<Address> getFarmAddress(String id);

    // Bank Accounts
    ResponseEntity<VendorResponseDto> addBankAccount(String id, BankAccount bankAccount);
    ResponseEntity<VendorResponseDto> updateBankAccount(String id, int accountIndex, BankAccount bankAccount);
    ResponseEntity<VendorResponseDto> removeBankAccount(String id, int accountIndex);
    ResponseEntity<List<BankAccount>> getBankAccounts(String id);

    // Password
    ResponseEntity<VendorResponseDto> updatePassword(String id, String currentPassword, String newPassword);

}
