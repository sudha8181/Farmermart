package com.tek.FarmerMart.UserMgmt.repository;

import com.tek.FarmerMart.UserMgmt.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {

    Optional<User> getByFirstName(String firstName);
    Optional<User> getBylastName(String lastName);


    String getByRole(String role);

    Optional<User> getByEmail(String email);

    User getByPhoneNo(String phoneNo);


    Optional<User> findByEmail(String email);

    @Query("{ 'email': ?0 }")
    User getUser(String email);

    @Query("select s from UserCollection s")
    List<User> getAllUser();

    Optional<User> findByUserId(String userId);
}
