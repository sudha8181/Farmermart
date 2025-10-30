package com.tek.FarmerMart.UserMgmt.service.imp;

import com.tek.FarmerMart.UserMgmt.dto.request.VendorRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.response.VendorResponseDto;
import com.tek.FarmerMart.UserMgmt.exception.vendor.AadhaarNumberException;
import com.tek.FarmerMart.UserMgmt.exception.vendor.PhoneNumberException;
import com.tek.FarmerMart.UserMgmt.exception.vendor.VendorNotFoundException;
import com.tek.FarmerMart.UserMgmt.model.Address;
import com.tek.FarmerMart.UserMgmt.model.BankAccount;
import com.tek.FarmerMart.UserMgmt.model.Vendor;
import com.tek.FarmerMart.UserMgmt.repository.VendorRepository;
import com.tek.FarmerMart.UserMgmt.service.SequenceGeneratorService;
import com.tek.FarmerMart.UserMgmt.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    @Autowired
    private  final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;
    private final SequenceGeneratorService service;

    // Vendor CRUD Operations

    public ResponseEntity<VendorResponseDto> createVendor(VendorRequestDto requestDto) {
        if (vendorRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new VendorNotFoundException("Vendor with this email already exists");
        }

        if (requestDto.getPhone() != null && vendorRepository.findByPhone(requestDto.getPhone()).isPresent()) {
            throw new PhoneNumberException("Vendor with this phone number already exists");
        }

        if (requestDto.getAadhaarNumber() != null && vendorRepository.findByAadhaarNumber(requestDto.getAadhaarNumber()).isPresent()) {
            throw new AadhaarNumberException("Vendor with this Aadhaar number already exists");
        }

        Vendor vendor = modelMapper.map(requestDto, Vendor.class);
        long seq = service.getNextSequence("vendor_seq");
            vendor.setVendorId(String.format("VNR%03d", seq));
//        vendor.setCreatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<VendorResponseDto> getVendorById(String id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id"));

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<VendorResponseDto> getVendorByEmail(String email) {
        Vendor vendor = vendorRepository.findByEmail(email)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with email: " + email));

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<VendorResponseDto> getVendorByPhone(String phone) {
        Vendor vendor = vendorRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Vendor not found with phone: " + phone));

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<VendorResponseDto> getVendorByAadhaar(String aadhaarNumber) {
        Vendor vendor = vendorRepository.findByAadhaarNumber(aadhaarNumber)
                .orElseThrow(() -> new RuntimeException("Vendor not found with Aadhaar: " + aadhaarNumber));

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<VendorResponseDto> getVendorByLinkedUserId(String linkedUserId) {
        Vendor vendor = vendorRepository.findByLinkedUserId(linkedUserId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with linked user id: " + linkedUserId));

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<List<VendorResponseDto>> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<VendorResponseDto> response = vendors.stream()
                .map(vendor -> modelMapper.map(vendor, VendorResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<VendorResponseDto> updateVendor(String id, VendorRequestDto requestDto) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        if (requestDto.getFirstName() != null) {
            existingVendor.setFirstName(requestDto.getFirstName());
        }
        if (requestDto.getLastName() != null) {
            existingVendor.setFirstName(requestDto.getLastName());
        }
        if (requestDto.getPhone() != null) {
            existingVendor.setPhone(requestDto.getPhone());
        }
        if (requestDto.getLinkedUserId() != null) {
            existingVendor.setLinkedUserId(requestDto.getLinkedUserId());
        }
        if (requestDto.getAadhaarNumber() != null) {
            existingVendor.setAadhaarNumber(requestDto.getAadhaarNumber());
        }
        if (requestDto.getFarmAddress() != null) {
            existingVendor.setFarmAddress(requestDto.getFarmAddress());
        }
        if (requestDto.getProduceType() != null) {
            existingVendor.setProduceType(String.valueOf(requestDto.getProduceType()));
        }
        if (requestDto.getBankAccounts() != null) {
            existingVendor.setBankAccounts(requestDto.getBankAccounts());
        }
        if (requestDto.getPassword() != null) {
            existingVendor.setPassword(requestDto.getPassword());
        }

//        existingVendor.setUpdatedAt(LocalDateTime.now());
//        long seq = service.getNextSequence("vendor_seq");
//        existingVendor.setVendorId(String.format("VNR%03d", seq));
        vendorRepository.save(existingVendor);

        VendorResponseDto response = modelMapper.map(existingVendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteVendor(String id) {
        if (!vendorRepository.findById(id).isPresent()) {
            throw new VendorNotFoundException("Vendor not found with id: " + id);
        }
        vendorRepository.deleteById(id);
        return new ResponseEntity<>("vendor delete successfully",HttpStatus.OK);
    }

    // Vendor Verification Operations

    public ResponseEntity<List<VendorResponseDto>> getVerifiedVendors() {
        List<Vendor> vendors = vendorRepository.findByVerified(true);
        List<VendorResponseDto> response = vendors.stream()
                .map(vendor -> modelMapper.map(vendor, VendorResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<VendorResponseDto>> getUnverifiedVendors() {
        List<Vendor> vendors = vendorRepository.findByVerified(false);
        List<VendorResponseDto> response = vendors.stream()
                .map(vendor -> modelMapper.map(vendor, VendorResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<VendorResponseDto> verifyVendor(String id) {
        if(!vendorRepository.findById(id).isPresent()){
            throw new VendorNotFoundException("Vendor not found with id:"+id);
        }
        Vendor vendor=vendorRepository.findById(id).get();
        vendor.setVerified(true);
//        vendor.setUpdatedAt(LocalDateTime.now());
        Vendor db=vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(db, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<VendorResponseDto> unverifyVendor(String id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + id));

        vendor.setVerified(false);
//        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Vendor Search & Filter Operations

    public ResponseEntity<List<VendorResponseDto>> getVendorsByProduceType(String produceType) {
        List<Vendor> vendors = vendorRepository.findByProduceType(produceType);
        List<VendorResponseDto> response = vendors.stream()
                .map(vendor -> modelMapper.map(vendor, VendorResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<VendorResponseDto>> searchVendorsByName(String firstName) {
        List<Vendor> vendors = vendorRepository.searchByNameIgnoreCase(firstName);
        List<VendorResponseDto> response = vendors.stream()
                .map(vendor -> modelMapper.map(vendor, VendorResponseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Farm Address Management

    @Transactional
    public ResponseEntity<VendorResponseDto> updateFarmAddress(String id, Address farmAddress) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        vendor.setFarmAddress(farmAddress);
//        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Address> getFarmAddress(String id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        Address address = vendor.getFarmAddress();
        if (address == null) {
            throw new RuntimeException("Farm address not found for vendor: " + id);
        }

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

//    // Bank Account Management

    @Transactional
    public ResponseEntity<VendorResponseDto> addBankAccount(String id, BankAccount bankAccount) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        if (vendor.getBankAccounts() == null) {
//            vendor.setBankAccounts(List.of(bankAccount));
            vendor.setBankAccounts(new ArrayList<>());

        } else {
//            bankAccount.setAccountId(UUID.randomUUID().toString());
            vendor.getBankAccounts().add(bankAccount);
        }

//        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<VendorResponseDto> updateBankAccount(String id, int accountIndex, BankAccount bankAccount) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + id));

        if (vendor.getBankAccounts() == null || vendor.getBankAccounts().isEmpty()) {
            throw new RuntimeException("No bank accounts found for this vendor");
        }

        if (accountIndex < 0 || accountIndex >= vendor.getBankAccounts().size()) {
            throw new RuntimeException("Invalid bank account index");
        }

        vendor.getBankAccounts().set(accountIndex, bankAccount);
//        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<VendorResponseDto> removeBankAccount(String id, int accountIndex) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        if (vendor.getBankAccounts() == null || vendor.getBankAccounts().isEmpty()) {
            throw new RuntimeException("No bank accounts found for this vendor");
        }

        if (accountIndex < 0 || accountIndex >= vendor.getBankAccounts().size()) {
            throw new RuntimeException("Invalid bank account index");
        }

        vendor.getBankAccounts().remove(accountIndex);
//        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<BankAccount>> getBankAccounts(String id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        List<BankAccount> accounts = vendor.getBankAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Password Management

    @Transactional
    public ResponseEntity<VendorResponseDto> updatePassword(String id, String currentPassword, String newPassword) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

        if (!vendor.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Current password is incorrect");
        }

        vendor.setPassword(newPassword);
//        vendor.setUpdatedAt(LocalDateTime.now());
        vendorRepository.save(vendor);

        VendorResponseDto response = modelMapper.map(vendor, VendorResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






    // Statistics & Counts

//    public ResponseEntity<Long> getTotalVendorCount() {
//        long count = vendorRepository.count();
//        return new ResponseEntity<>(count, HttpStatus.OK);
//    }
//
//    public ResponseEntity<Long> getVerifiedVendorCount() {
//        long count = vendorRepository.findByVerified(true).size();
//        return new ResponseEntity<>(count, HttpStatus.OK);
//    }
//
//    public ResponseEntity<Long> getUnverifiedVendorCount() {
//        long count = vendorRepository.findByVerified(false).size();
//        return new ResponseEntity<>(count, HttpStatus.OK);
//    }
//}
}
