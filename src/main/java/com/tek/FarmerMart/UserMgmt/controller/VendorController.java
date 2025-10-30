package com.tek.FarmerMart.UserMgmt.controller;

import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import com.tek.FarmerMart.UserMgmt.dto.request.VendorRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.response.VendorResponseDto;
import com.tek.FarmerMart.UserMgmt.service.VendorService;
import com.tek.FarmerMart.UserMgmt.service.imp.VendorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class VendorController {

    private final VendorServiceImpl vendorService;


    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<VendorResponseDto> createVendor(@RequestBody VendorRequestDto requestDto) {
        return vendorService.createVendor(requestDto);
    }

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @GetMapping("/profile/{id}")
    public ResponseEntity<VendorResponseDto> getVendorById(@PathVariable String id) {
        return vendorService.getVendorById(id);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/email")
    public ResponseEntity<VendorResponseDto> getVendorByEmail(@RequestParam String email) {
        return vendorService.getVendorByEmail(email);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/phone")
    public ResponseEntity<VendorResponseDto> getVendorByPhone(@RequestParam String phone) {
        return vendorService.getVendorByPhone(phone);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/aadhaarNumber")
    public ResponseEntity<VendorResponseDto> getVendorByAadhaar(@RequestParam String aadhaarNumber) {
        return vendorService.getVendorByAadhaar(aadhaarNumber);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/linkedUserId")
    public ResponseEntity<VendorResponseDto> getVendorByLinkedUserId(@RequestParam String linkedUserId) {
        return vendorService.getVendorByLinkedUserId(linkedUserId);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list-all")
    public ResponseEntity<List<VendorResponseDto>> getAllVendors() {
        return vendorService.getAllVendors();
    }

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @PatchMapping("/profile/update/{id}")
    public ResponseEntity<VendorResponseDto> updateVendor(
            @PathVariable String id,
            @RequestBody VendorRequestDto requestDto) {
        return vendorService.updateVendor(id, requestDto);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable String id) {
        return vendorService.deleteVendor(id);
    }

    // Verification Endpoints

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/verified")
    public ResponseEntity<List<VendorResponseDto>> getVerifiedVendors() {
        return vendorService.getVerifiedVendors();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unverified")
    public ResponseEntity<List<VendorResponseDto>> getUnverifiedVendors() {
        return vendorService.getUnverifiedVendors();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/verify/{id}")
    public ResponseEntity<VendorResponseDto> verifyVendor(@PathVariable("id") String id) {
        return vendorService.verifyVendor(id);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/unverify/{id}")
    public ResponseEntity<VendorResponseDto> unverifyVendor(@PathVariable String id) {
        return vendorService.unverifyVendor(id);
    }

    // Search & Filter Endpoints

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/produce-type/{produceType}")
    public ResponseEntity<List<VendorResponseDto>> getVendorsByProduceType(@PathVariable String produceType) {
        return vendorService.getVendorsByProduceType(produceType);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/name")
    public ResponseEntity<List<VendorResponseDto>> searchVendorsByName(@RequestParam String name) {
        return vendorService.searchVendorsByName(name);
    }

    // Farm Address Endpoints

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @PutMapping("/update-farm-address/{id}")
    public ResponseEntity<VendorResponseDto> updateFarmAddress(
            @PathVariable String id,
            @RequestBody Address farmAddress) {
        return vendorService.updateFarmAddress(id, farmAddress);
    }

//    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
//    @GetMapping("/farm_address/{id}")
//    public ResponseEntity<Address> getFarmAddress(@PathVariable String id) {
//        return vendorService.getFarmAddress(id);
//    }

    // Bank Account Endpoints

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @PostMapping("/{id}/add-bank-account")
    public ResponseEntity<VendorResponseDto> addBankAccount(
            @PathVariable String id,
            @RequestBody BankAccount bankAccount) {
        return vendorService.addBankAccount(id, bankAccount);
    }

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @PutMapping("/{id}/update-bank-account/{accountIndex}")
    public ResponseEntity<VendorResponseDto> updateBankAccount(
            @PathVariable String id,
            @PathVariable int accountIndex,
            @RequestBody BankAccount bankAccount) {
        return vendorService.updateBankAccount(id, accountIndex, bankAccount);
    }

    //@PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @DeleteMapping("/{id}/remove-bank-accounts/{accountIndex}")
    public ResponseEntity<VendorResponseDto> removeBankAccount(
            @PathVariable String id,
            @PathVariable int accountIndex) {
        return vendorService.removeBankAccount(id, accountIndex);
    }

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @GetMapping("/{id}/list-bank-accounts")
    public ResponseEntity<List<BankAccount>> getBankAccounts(@PathVariable String id) {
        return vendorService.getBankAccounts(id);
    }


    // Password Management Endpoint

    // @PreAuthorize("hasRole('ADMIN') or @vendorSecurityService.isVendorOwner(#id)")
    @PatchMapping("/{id}/change-password")
    public ResponseEntity<VendorResponseDto> updatePassword(
            @PathVariable String id,
            @RequestBody Map<String, String> passwordData) {
        String currentPassword = passwordData.get("currentPassword");
        String newPassword = passwordData.get("newPassword");

        return vendorService.updatePassword(id, currentPassword, newPassword);
    }
}






// Statistics Endpoints

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/stats/total")
//    public ResponseEntity<Long> getTotalVendorCount() {
//        return vendorService.getTotalVendorCount();
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/stats/verified")
//    public ResponseEntity<Long> getVerifiedVendorCount() {
//        return vendorService.getVerifiedVendorCount();
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/stats/unverified")
//    public ResponseEntity<Long> getUnverifiedVendorCount() {
//        return vendorService.getUnverifiedVendorCount();
//    }

