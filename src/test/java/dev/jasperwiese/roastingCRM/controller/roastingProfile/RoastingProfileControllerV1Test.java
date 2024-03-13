package dev.jasperwiese.roastingCRM.controller.roastingProfile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;
import dev.jasperwiese.roastingCRM.service.RoastingProfileService;
import dev.jasperwiese.roastingCRM.service.impl.RoastingProfileServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = RoastingProfileControllerV1.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class RoastingProfileControllerV1Test {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RoastingProfileServiceImpl roastingProfileService;
    private RoastingProfileDto roastingProfileDto;
    private ClientAddRoastingProfileRequest addRoastingProfileRequest;

    @BeforeEach
    void setUp(){
        //Adding roastingProfileDto for use in the tests below, as mocked data
        roastingProfileDto = RoastingProfileDto.builder()
                .roastingProfileId("581213cb-37d6-4124-ac5f-7d694f7bd570")
                .profileName("sample profile name")
                .roasterModel("roaster model sample")
                .notes("sample notes")
                .greenBeansDto(
                        GreenBeansDto.builder()
                                .greenBeansId("6d4cbbde-8560-4d26-a7ac-11f392c49bc0")
                                .field("sample field")
                                .region("Uganda")
                                .grade("AA")
                                .flavour("Bold")
                                .body("Full")
                                .acidity("Low")
                                .process("Shelled")
                                .moisture("10%")
                                .packaging("Box")
                                .notes("High Quality Bean.")
                                .build()
                )
                .targetTemperatureDto(
                        TargetTemperatureDto.builder()
                                .targetTemperatureId("625fd101-d771-4420-b46a-6a787df091ac")
                                .preheat("180")
                                .firstCrack("200")
                                .development("250")
                                .drop("260")
                                .build()
                )
                .timeIntervalsDto(
                        TimeIntervalsDto.builder()
                                .timeIntervalsId("9c717e6e-237d-4c21-9758-4d4b9d43beb0")
                                .preheat("180")
                                .firstCrack("300")
                                .development("500")
                                .total("650")
                                .build()
                )
                .airFlowSettingsDto(
                        AirFlowSettingsDto.builder()
                                .airFlowSettingsId("9c35be37-a922-4a38-8c10-22ab7cf6f6e7")
                                .low("50")
                                .medium("100")
                                .high("200")
                                .build()
                )
                .build();
        //adding ClientAddRoastingProfileRequest for mocking data in the UT below.
        addRoastingProfileRequest = ClientAddRoastingProfileRequest.builder()
                .profileName("Beans' Afternoon sunset")
                .notes("Stock Afternoon sunset")
                .clientId("de7f51fd-ebf9-43a3-b3f7-ff3358ec360b")
                .roastingProfileDto(
                        RoastingProfileDto.builder()
                                .profileName("Afternoon sunset")
                                .roasterModel("LaMazzorco")
                                .notes("Dark roast")
                                .greenBeansDto(
                                        GreenBeansDto.builder()
                                                .field("Hen")
                                                .region("Kenya")
                                                .grade("AA")
                                                .flavour("Sweet")
                                                .body("Bold")
                                                .acidity("low")
                                                .process("Barrel")
                                                .moisture("10%")
                                                .packaging("Card")
                                                .notes("ideal for dark roasting")
                                                .build()
                                )
                                .targetTemperatureDto(
                                        TargetTemperatureDto.builder()
                                                .preheat("120")
                                                .firstCrack("150")
                                                .development("200")
                                                .build()
                                )
                                .timeIntervalsDto(
                                        TimeIntervalsDto.builder()
                                                .preheat("60")
                                                .firstCrack("120")
                                                .development("180")
                                                .total("220")
                                                .build()
                                )
                                .airFlowSettingsDto(
                                        AirFlowSettingsDto.builder()
                                                .low("50")
                                                .medium("80")
                                                .high("100")
                                                .build()
                                )
                                .build()
                )
                .build();

    }

    @Test
    void itShouldAddClientRoastingProfile() throws Exception {
        //Given
        given(roastingProfileService.createClientRoastingProfile(any(ClientAddRoastingProfileRequest.class))).willReturn(roastingProfileDto);
        //When
        ResultActions response = mockMvc.perform(post("/api/v1/roasting-profile/client/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roastingProfileDto))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roastingProfileId", CoreMatchers.is(roastingProfileDto.getRoastingProfileId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profileName", CoreMatchers.is(roastingProfileDto.getProfileName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roasterModel", CoreMatchers.is(roastingProfileDto.getRoasterModel())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes", CoreMatchers.is(roastingProfileDto.getNotes())));
    }

    @Test
    void itShouldGetAllRoastingProfiles() throws Exception {
        //Given
        given(roastingProfileService.getAllRoastingProfiles()).willReturn(List.of(roastingProfileDto));
        //When
        ResultActions response = mockMvc.perform(get("/api/v1/roasting-profile/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(roastingProfileDto)))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)));
    }

    @Test
    void itShouldGetRoastingProfileById() throws Exception {
        //Given
        given(roastingProfileService.getRoastingProfileById(any(String.class))).willReturn(roastingProfileDto);
        //When
        ResultActions response = mockMvc.perform(
                get("/api/v1/roasting-profile/{roastingProfileId}", "581213cb-37d6-4124-ac5f-7d694f7bd570")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roastingProfileDto))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roastingProfileId").value("581213cb-37d6-4124-ac5f-7d694f7bd570"));
    }

    @Test
    void itShouldGetClientRoastingProfiles() throws Exception {
        //Given
        given(roastingProfileService.getAllRoastingProfilesOfClient(any(String.class))).willReturn(List.of(roastingProfileDto));
        //When
        ResultActions response = mockMvc.perform(
                get("/api/v1/roasting-profile/client/{clientId}","de7f51fd-ebf9-43a3-b3f7-ff3358ec360b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roastingProfileDto))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    void itShouldDeleteRoastingProfileById() throws Exception {
        //Given
        String roastingProfileId = "581213cb-37d6-4124-ac5f-7d694f7bd570";
        doNothing().when(roastingProfileService).deleteRoastingProfileById(roastingProfileId);
        //When
        ResultActions response = mockMvc.perform(delete("/api/v1/roasting-profile/delete/{roastingProfileId}", roastingProfileId)
                .contentType(MediaType.APPLICATION_JSON)
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
        verify(roastingProfileService, times(1)).deleteRoastingProfileById(roastingProfileId);
    }
}