package com.tek.FarmerMart.UserMgmt.repository;

import com.tek.FarmerMart.UserMgmt.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends MongoRepository<Vendor,String> {



    // Find vendor by email
    Optional<Vendor> findByEmail(String email);

    // Find vendors by verification status
    List<Vendor> findByVerified(boolean verified);

    // Find vendors by produce type
    List<Vendor> findByProduceType(String produceType);

    // Search vendors by name
    @Query("SELECT v FROM Vendor v WHERE LOWER(v.first_name) LIKE LOWER(CONCAT('%', :first_name, '%'))")
    List<Vendor> searchByNameIgnoreCase(@Param("first_name") String firstName);

    // Find vendor by phone number
    Optional<Vendor> findByPhone(String phone);

    // Find vendor by Aadhaar number
    Optional<Vendor> findByAadhaarNumber(String aadhaarNumber);

    // Find vendor by linked user ID
    Optional<Vendor> findByLinkedUserId(String linkedUsrId);

    @Override
    Optional<Vendor> findById(String s);

    @Override
    void deleteById(String s);
}
