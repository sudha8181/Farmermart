package com.tek.FarmerMart.UserMgmt.service.imp;

import com.tek.FarmerMart.UserMgmt.dto.request.LoginDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserProfileRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserRegistrationRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.request.UserVendorDto;
import com.tek.FarmerMart.UserMgmt.dto.response.UserProfileResponseDto;
import com.tek.FarmerMart.UserMgmt.dto.response.UserRegistrationResponseDto;
import com.tek.FarmerMart.UserMgmt.exception.user.PasswordMisMatchException;
import com.tek.FarmerMart.UserMgmt.exception.user.UserNotFoundException;
import com.tek.FarmerMart.UserMgmt.exception.vendor.AadhaarNumberException;
import com.tek.FarmerMart.UserMgmt.exception.vendor.PhoneNumberException;
import com.tek.FarmerMart.UserMgmt.exception.vendor.VendorNotFoundException;
import com.tek.FarmerMart.UserMgmt.model.User;
import com.tek.FarmerMart.UserMgmt.model.Vendor;
import com.tek.FarmerMart.UserMgmt.repository.UserRepo;
import com.tek.FarmerMart.UserMgmt.repository.VendorRepository;
import com.tek.FarmerMart.UserMgmt.service.SequenceGeneratorService;
import com.tek.FarmerMart.UserMgmt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepo userRepo;
    private final VendorRepository vendorRepo;
    private final ModelMapper modelMapper;
    private final SequenceGeneratorService service;


//    private String generateUUID() {
//        return "USR-" + UUID.randomUUID().toString().substring(0,5).toUpperCase();
//    }


//    @Autowired
//    public UserServiceImp(UserRepo userRepo, ModelMapper modelMapper) {
//        this.userRepo = userRepo;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public ResponseEntity<UserRegistrationResponseDto> signup(UserRegistrationRequestDto request) {

        if(userRepo.getByEmail(request.getEmail()).isPresent()) {
            throw new UserNotFoundException("User is already exists");
        }


        if (request.getPhoneNo() != null && userRepo.findByPhoneNo(request.getPhoneNo()).isPresent()) {
            throw new PhoneNumberException("User with this phone number already exists");
        }

        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw  new PasswordMisMatchException("password should match with confirm password");
        }
        // Map DTO to entity

        User user = modelMapper.map(request, User.class);
        long seq = service.getNextSequence("user_seq");
        user.setUserId(String.format("USR%03d", seq));
       // user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        User user1=userRepo.save(user);

        // Map entity to DTO
        UserRegistrationResponseDto response=modelMapper.map(user1, UserRegistrationResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<UserRegistrationResponseDto> login(LoginDto loginDto) {
       if(!userRepo.findByEmail(loginDto.getEmail()).isPresent()){
           throw new UserNotFoundException("user does not exist with specified email");
       }
       User user=userRepo.findByEmail(loginDto.getEmail()).get();
       if(!user.getPassword().equals(loginDto.getPassword())){
           throw new UserNotFoundException("incorrect username and password");
       };


       //Map entity to dto
        UserRegistrationResponseDto responseDto=modelMapper.map(user, UserRegistrationResponseDto.class);
       return new ResponseEntity<>(responseDto,HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<UserProfileResponseDto> getUser(String userId) {
       if(!userRepo.findByUserId(userId).isPresent()){
           throw new UserNotFoundException("User does not exist.");
       }
       Optional<User> user=userRepo.findByUserId(userId);
        User user1=user.get();

        //entity to Dto
        UserProfileResponseDto userResp=modelMapper.map(user1, UserProfileResponseDto.class);
        return new ResponseEntity<>(userResp, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<UserRegistrationResponseDto>> getAllUser() {
        List<User> users = userRepo.getAllUser();
        List<UserRegistrationResponseDto> userResp=new ArrayList<>();
        //entity to Dto
        for(User u:users){
            UserRegistrationResponseDto user=modelMapper.map(u, UserRegistrationResponseDto.class);
            userResp.add(user);
        }

        return new ResponseEntity<>(userResp, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> deactivateUser(String id) {
     if(!userRepo.findByUserId(id).isPresent()){
         throw new UserNotFoundException("User does not found");
     }
     User u=userRepo.findByUserId(id).get();
     userRepo.delete(u);

     return ResponseEntity.ok("User deactivate Successfully");


    }

    @Override
    public ResponseEntity<UserProfileResponseDto> updateProfile(String id, UserProfileRequestDto request) {
        if(!userRepo.findByUserId(id).isPresent()){
            throw new UserNotFoundException("user does not exist");
        }

        User existinguser=userRepo.findByUserId(id).get();
        System.out.println(existinguser.getEmail());

        if(request.getFirstName()!=null){
            existinguser.setFirstName(request.getFirstName());
        }

        if(request.getLastName()!=null){
            existinguser.setLastName(request.getLastName());
        }
        //email
        if(request.getEmail()!=null){
            existinguser.setEmail(request.getEmail());
        }

        //aleternate email
        if(request.getAlternateEmail()!=null){
            existinguser.setAlternateEmail(request.getAlternateEmail());
        }

        if(request.getPhoneNo()!=null){
            existinguser.setPhoneNo(request.getPhoneNo());
        }
        if(request.getAlternatePhoneNo()!=null){
            existinguser.setAlternatePhoneNo(request.getAlternatePhoneNo());
        }

        if(request.getBankList()!=null){
            existinguser.setBankAccounts(request.getBankList());
        }
        if(request.getAddresses()!=null){
            existinguser.setAddresses(request.getAddresses());
        }
        if(request.getPassword()!=null){
            existinguser.setPassword(request.getPassword());
        }
//        existinguser.setUpdatedAt(LocalDateTime.now());



        User db=userRepo.save(existinguser);
        //Dto to Entity
        UserProfileResponseDto user1=modelMapper.map(db,UserProfileResponseDto.class);
        return new ResponseEntity<>(user1,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> UserToVendor(UserVendorDto request) {

        User user = userRepo.findByEmail(request.getEmail()).get();
        Vendor vendor=new Vendor();
        vendor.setFirstName(user.getFirstName());
        vendor.setLastName(user.getLastName());
        vendor.setEmail(user.getEmail());
        vendor.setPhone(user.getPhoneNo());
        vendor.setLinkedUserId(user.getUserId());
        vendor.setBankAccounts(user.getBankAccounts());
        vendor.setPassword(user.getPassword());
//        vendor.setCreatedAt(LocalDateTime.now());
        vendor.setVerified(false);
        vendorRepo.save(vendor);

        return ResponseEntity.ok("User to vendor");

    }

    @Override
    public Optional<User> getByFirstName(String firstName) {
        return userRepo.getByFirstName(firstName);
    }

    @Override
    public void deleteByUserId(String s) {
        userRepo.deleteById(s);
    }


}
