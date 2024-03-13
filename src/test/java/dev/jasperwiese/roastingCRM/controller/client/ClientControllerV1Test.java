package dev.jasperwiese.roastingCRM.controller.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.service.impl.ClientServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ClientControllerV1.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ClientControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientServiceImpl clientService;

    private ClientDto clientDto;
    private ClientCreationRequest clientCreationRequest;

    @BeforeEach
    void setUp() {
        //Initialising dummy data for the unit tests: ClientDto, ClientCreationRequest
        //addressDtoList to be added to the ClientDto
        List<AddressDto> addressDtoList = List.of(
                AddressDto.builder()
                        .buildingUnitNumber("54")
                        .streetAddress("46 Maple Leaf avenue")
                        .city("Cape Town")
                        .districtSuburb("Tafelsig")
                        .stateProvince("Western Cape")
                        .postalCode("7134")
                        .country("South Africa")
                        .build()
        );
        //contactPersonDtoList to be added to the ClientDto
        List<ContactPersonDto> contactPersonDtoList = List.of(
          ContactPersonDto.builder()
                  .firstName("Bob")
                  .lastName("Heckler")
                  .positionHeld("Chief")
                  .contactDetailsDto(
                          ContactDetailsDto.builder()
                                  .personalPhone("+27654346345")
                                  .workPhone("+27654346345")
                                  .businessEmail("business@business.com")
                                  .email("personal@personal.com")
                                  .build()
                  )
                  .build()
        );
        List<RoastingProfileDto> roastingProfileDtoList = new ArrayList<>();
        //adding roastProfile dtos
        roastingProfileDtoList.add(RoastingProfileDto.builder()
                .profileName("Light Roast Ugandan Sunshine")
                .roasterModel("Evvecco 500")
                .greenBeansDto(
                        GreenBeansDto.builder()
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
                                .preheat("180")
                                .firstCrack("200")
                                .development("250")
                                .drop("260")
                                .build()
                )
                .timeIntervalsDto(
                        TimeIntervalsDto.builder()
                                .preheat("180")
                                .firstCrack("300")
                                .development("500")
                                .total("650")
                                .build()
                )
                .airFlowSettingsDto(
                        AirFlowSettingsDto.builder()
                                .low("50")
                                .medium("100")
                                .high("200")
                                .build()
                )
                .notes("test")
                .build());
        //Init clientDto with list data of addressDtoList, contactPersonDtoList and roastingProfileDtoList
        clientDto = ClientDto.builder()
                .companyName("sample company")
                .vatNumber("23412122")
                .addressDtoList(addressDtoList)
                .contactPersonDtoList(contactPersonDtoList)
                .roastingProfileDtoList(roastingProfileDtoList)
                .build();
        //Init ClientCreationRequest
        clientCreationRequest = ClientCreationRequest.builder()
                .companyName("sample company")
                .vatNumber("23412122")
                .addressDtoList(addressDtoList)
                .clientContactDtoList(contactPersonDtoList)
                .build();

    }

    @Test
    void itShouldCreateClientReturnCreated() throws Exception {
        //Given
        given(clientService.createClient(any(ClientCreationRequest.class))).willAnswer(invocation -> invocation.getArgument(0));
        //When
        ResultActions response = mockMvc.perform(post("/api/v1/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientCreationRequest))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName", CoreMatchers.is(clientCreationRequest.getCompanyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vatNumber", CoreMatchers.is(clientCreationRequest.getVatNumber())));
    }

    @Test
    void itShouldGetAllClients() throws Exception {
        //Given
        given(clientService.getAllClients()).willReturn(List.of(clientDto));
        //When
        ResultActions response = mockMvc.perform(get("/api/v1/client/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(clientDto)))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName", CoreMatchers.is(clientDto.getCompanyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vatNumber", CoreMatchers.is(clientDto.getVatNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressDtoList.size()", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contactPersonDtoList.size()", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roastingProfileDtoList.size()", CoreMatchers.is(1)));
    }

    @Test
    void itShouldGetClientById() throws Exception {
        //Given
        given(clientService.findClientById(any(String.class))).willReturn(clientDto);
        //When
        ResultActions response = mockMvc.perform(get("/api/v1/client/{clientId}", "6c40fb78-2510-45ee-ba1a-a6f5b78c03cf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDto))
        );
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName", CoreMatchers.is(clientDto.getCompanyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vatNumber", CoreMatchers.is(clientDto.getVatNumber())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressDtoList.size()", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactPersonDtoList.size()", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roastingProfileDtoList.size()", CoreMatchers.is(1)));
    }
}