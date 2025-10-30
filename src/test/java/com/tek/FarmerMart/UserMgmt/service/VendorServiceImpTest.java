package com.tek.FarmerMart.UserMgmt.service;

import com.tek.FarmerMart.UserMgmt.dto.request.VendorRequestDto;
import com.tek.FarmerMart.UserMgmt.dto.response.VendorResponseDto;
import com.tek.FarmerMart.UserMgmt.enums.Role;
import com.tek.FarmerMart.UserMgmt.model.Vendor;
import com.tek.FarmerMart.UserMgmt.repository.VendorRepository;
import com.tek.FarmerMart.UserMgmt.service.imp.VendorServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImpTest {


    @Mock
    private VendorRepository vendorRepo;

    @Mock
    private ModelMapper mapper;


    @Mock
    private SequenceGeneratorService sequenceGenerator;

    @InjectMocks
    private VendorServiceImpl service;

    @Disabled
    @Test
    void testCreateVendor_ShouldSaveAndReturnVendorResponse(){
        VendorRequestDto requestDto=new VendorRequestDto();
        requestDto.setFirstName("sudha");
        requestDto.setLastName("gurung");
        requestDto.setPhone("334554565456");
        requestDto.setPassword("123123");
        requestDto.setEmail("sudhagrg8034@gmail.com");

        Vendor v=new Vendor();
        v.setVendorId("VNR01");
        v.setFirstName(requestDto.getFirstName());
        v.setLastName(requestDto.getLastName());
        v.setEmail(requestDto.getEmail());
        v.setPhone(requestDto.getPhone());
        v.setPassword(requestDto.getPassword());
        v.setPassword(requestDto.getPassword());


        VendorResponseDto responseDto = new VendorResponseDto();
        responseDto.setId(v.getVendorId());
        responseDto.setFirstName(v.getFirstName());
        responseDto.setEmail(v.getEmail());

        //mocking
        when(mapper.map(requestDto,Vendor.class)).thenReturn(v);
        when(vendorRepo.save(v)).thenReturn(v);
        when(mapper.map(v,VendorResponseDto.class)).thenReturn(responseDto);



        //act
        VendorResponseDto dto=service.createVendor(requestDto).getBody();

        // Assert
        assertNotNull(dto, "Response should not be null");
        assertEquals("VNR01", dto.getId());
        assertEquals("sudha", dto.getFirstName());
        assertEquals("sudhagrg8034@gmail.com",dto.getEmail());


        // Verify interactions
        verify(mapper, times(1)).map(requestDto, Vendor.class);
        verify(vendorRepo, times(1)).save(v);
        verify(mapper, times(1)).map(v, VendorResponseDto.class);
    }



    @Test
    void testGetVendorByEmail_ShouldReturnVendorResponse(){
         String email="sudhagrg8034@gmail.com";
         Vendor v=new Vendor();
         v.setVendorId("VNR01");
         v.setEmail("sudhagrg8034@gmail.com");
//         v.setVendorId("user001");
         v.setFirstName("sudha");
         v.setLastName("raman");

        VendorResponseDto responseDto = new VendorResponseDto();
        responseDto.setId(v.getVendorId());
        responseDto.setFirstName(v.getFirstName());
        responseDto.setEmail(email);

         when(vendorRepo.findByEmail(email)).thenReturn(Optional.of(v));
         when(mapper.map(v,VendorResponseDto.class)).thenReturn(responseDto);



         //act
        VendorResponseDto dto=service.getVendorByEmail(email).getBody();

        // Assert
        assertNotNull(dto, "Response should not be null");
        assertEquals("VNR01", dto.getId());
        assertEquals("sudha", dto.getFirstName());
        assertEquals(email, dto.getEmail());

        verify(vendorRepo, times(1)).findByEmail(email);
        verify(mapper, times(1)).map(v, VendorResponseDto.class);

    }

    @Test
    void testGetVendorByLinkedUserId_ShouldReturnVendorResponse(){
        String email="sudhagrg8034@gmail.com";
        Vendor v=new Vendor();
        v.setVendorId("VNR01");
        v.setEmail("sudhagrg8034@gmail.com");
//         v.setVendorId("user001");
        v.setFirstName("sudha");
        v.setLastName("raman");

        VendorResponseDto responseDto = new VendorResponseDto();
        responseDto.setId(v.getVendorId());
        responseDto.setFirstName(v.getFirstName());
        responseDto.setEmail(email);

        when(vendorRepo.findByEmail(email)).thenReturn(Optional.of(v));
        when(mapper.map(v,VendorResponseDto.class)).thenReturn(responseDto);



        //act
        VendorResponseDto dto=service.getVendorByEmail(email).getBody();

        // Assert
        assertNotNull(dto, "Response should not be null");
        assertEquals("VNR01", dto.getId());
        assertEquals("sudha", dto.getFirstName());
        assertEquals(email, dto.getEmail());

        verify(vendorRepo, times(1)).findByEmail(email);
        verify(mapper, times(1)).map(v, VendorResponseDto.class);
    }

    @Test
    void testUpdateVendorByLinkedUserId_ShouldReturnVendorResponse(){

    }

    @Test
    void testDeleteVendor_ShouldReturnVendorResponse(){
        String vendor_id="VNR001";
          Vendor v=new Vendor();
          v.setVendorId("VNR001");

          //Mockito
          when(vendorRepo.findById(vendor_id)).thenReturn(Optional.of(v));
          doNothing().when(vendorRepo).deleteById(vendor_id);

          //act
        String S=service.deleteVendor(vendor_id).toString();

        //assert
        assertEquals("VNR001",v.getVendorId());


verify(vendorRepo,times(1)).deleteById(vendor_id);


    }

    @Test
    void testSearchVendor_ShouldReturnVendorResponse(){




    }

    @Test
    void testAddAccountByVendor_ShouldReturnVendorResponse(){


    }

    @Test
    void testRemoveAccountFromVendor_ShouldReturnVendorResponse(){



    }

    @Test
    void testGetBankAccountVendor_ShouldReturnVendorResponse(){


    }
}
