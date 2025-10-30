package com.tek.FarmerMart.UserMgmt.service;

import com.tek.FarmerMart.UserMgmt.dto.request.UserRegistrationRequestDto;
import com.tek.FarmerMart.UserMgmt.model.User;
import com.tek.FarmerMart.UserMgmt.repository.UserRepo;
import com.tek.FarmerMart.UserMgmt.service.imp.UserServiceImp;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;






@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;


    @Mock
    private SequenceGeneratorService sequenceGenerator;

    @InjectMocks
    private UserServiceImp user_service;


//     @Disabled
@ParameterizedTest
@ValueSource(strings = {"ram12@gmail.com", "shyam122@gmail.com", "vipulraj12@gmail.com", "Raj123@gmail.com", "sandeep80@gmail.com"})
 void testFindByEmail(String email){
   UserRegistrationRequestDto u=new UserRegistrationRequestDto();
   u.setEmail(email);
   u.setPassword("12345");
   u.setConfirmPassword("12345");
   User user=new User();
   user.setEmail(email);

   Mockito.when(userRepo.save(user)).thenReturn(user);
   Mockito.when(modelMapper.map(u,User.class)).thenReturn(user);

   User u1=modelMapper.map(u,User.class);
   user_service.signup(u);

   Assertions.assertEquals(email,u1.getEmail());




}

    @ParameterizedTest
    @ValueSource(strings = {"ram", "shyam", "vipul", "Raj"})
    public void testFindByFirstName(String firstName){

       UserRegistrationRequestDto u=new UserRegistrationRequestDto();
       u.setFirstName(firstName);
       u.setEmail("sudhagrg8034gmail.com");
       u.setPassword("12345");
       u.setConfirmPassword("12345");
//       User user=modelMapper.map(u,User.class);
        User user=new User();
        user.setFirstName(firstName);
        Mockito.when(modelMapper.map(u,User.class)).thenReturn(user);
        User u1=modelMapper.map(u,User.class);
        Mockito.when(sequenceGenerator.getNextSequence("user_seq")).thenReturn(1L);

        long seq = sequenceGenerator.getNextSequence("user_seq");
        u1.setUserId(String.format("USR%03d", seq));


        Mockito.when(userRepo.save(u1)).thenReturn(u1);
      user_service.signup(u);
//       assertNotNull(u1,"User should not be null for me name :"+firstName);
       Assertions.assertEquals(firstName,u1.getFirstName());
    }

    @AfterAll
    public static  void cleanup(){
        System.out.println("clean uo only once");
    }

    @AfterEach
    public void destroy(){
     System.out.println("clean up for each test case");
    }


    @Test
    public void deleteByIdOnlyOnce(){


        String userId="user001";

     Mockito.doNothing().when(userRepo).deleteById(userId);
        user_service.deactivateUser(userId);
        System.out.println(userId);
        verify(userRepo, times(1)).deleteById(userId);

    }




}
