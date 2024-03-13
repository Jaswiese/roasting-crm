package dev.jasperwiese.roastingCRM.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.dto.EmergencyContactDto;
import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserControllerV1.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerV1Test {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserServiceImpl userService;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
       AddressDto addressDto = AddressDto.builder()
                .buildingUnitNumber("54")
                .streetAddress("46 Maple Leaf avenue")
                .city("Cape Town")
                .districtSuburb("Tafelsig")
                .stateProvince("Western Cape")
                .postalCode("7134")
                .country("South Africa")
                .build();

      ContactDetailsDto  contactDetailsDto = ContactDetailsDto.builder()
                .email("beb@gmail.com")
                .businessEmail("beb@hoof.com")
                .workPhone("+27654455466")
                .personalPhone("+27654455543")
                .build();

      EmergencyContactDto emergencyContactDto = EmergencyContactDto.builder()
                .firstName("Bob")
                .lastName("Geese")
                .email("bob.geese@hoola.com")
                .phoneNumber("+278574466978")
                .relationship("father")
                .build();

        userDto = UserDto.builder()
                .verified(true)
                .firstName("Jeff")
                .lastName("mynameis")
                .password("password")
                .position("Roasting Technician")
                .role("admin")
                .contactDetailsDto(contactDetailsDto)
                .emergencyContactDto(emergencyContactDto)
                .addressDtoList(List.of(addressDto))
                .build();
    }

    @Test
    void itShouldCreateUser() throws Exception {
        //Given
        given(userService.createUser(any(UserDto.class))).willReturn(userDto);
        //When
        ResultActions response = mockMvc.perform(
                post("/api/v1/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.verified").value(true));
    }

    @Test
    void itShouldGetUser() throws Exception {
        //Given
        String userId = "3f744e7e-b3c6-49a1-a1d7-169729b0a868";
        userDto.setUserId(userId);
        given(userService.getUserById(any(String.class))).willReturn(userDto);
        //When
        ResultActions response = mockMvc.perform(
                get("/api/v1/user/{userId}", "3f744e7e-b3c6-49a1-a1d7-169729b0a868")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(userId));
    }

    @Test
    void itShouldGetUsers() throws Exception{
        //Given
        given(userService.getUsers()).willReturn(List.of(userDto));
        //When
        ResultActions response = mockMvc.perform(
                get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }
}