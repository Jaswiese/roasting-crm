package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.dto.EmergencyContactDto;
import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.EmergencyContact;
import dev.jasperwiese.roastingCRM.entity.user.User;
import dev.jasperwiese.roastingCRM.repository.AddressRepository;
import dev.jasperwiese.roastingCRM.repository.ContactDetailsRepository;
import dev.jasperwiese.roastingCRM.repository.EmergencyContactRepository;
import dev.jasperwiese.roastingCRM.repository.UserRepository;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.user.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/*
* This test class needs to be expanded & Service class logic improved
* TODO: add logic checks for null or empty for UserDto (contactDetailsDto, emergencyContactDetailsDto, addressDtoList)
* TODO: rewrite these tests with ArgumentCaptor*/
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserServiceImpl underTest;
    private AddressDto addressDto;
    private ContactDetailsDto contactDetailsDto;
    private EmergencyContactDto emergencyContactDto;
    private UserDto userDto;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private EmergencyContactRepository emergencyContactRepository;
    @Mock
    private ContactDetailsRepository contactDetailsRepository;

    @BeforeEach
    void setUp() {

        underTest = new UserServiceImpl(
                userRepository,
                userMapper,
                addressMapper,
                addressRepository,
                emergencyContactRepository,
                contactDetailsRepository
        );

        addressDto = AddressDto.builder()
                .buildingUnitNumber("54")
                .streetAddress("46 Maple Leaf avenue")
                .city("Cape Town")
                .districtSuburb("Tafelsig")
                .stateProvince("Western Cape")
                .postalCode("7134")
                .country("South Africa")
                .build();

        contactDetailsDto = ContactDetailsDto.builder()
                .email("beb@gmail.com")
                .businessEmail("beb@hoof.com")
                .workPhone("+27654455466")
                .personalPhone("+27654455543")
                .build();

        emergencyContactDto = EmergencyContactDto.builder()
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
    void itShouldCreateUser() {
        // Given
        when(userMapper.mapToEntity(any())).thenReturn(new User());
        when(userMapper.mapToDto(any())).thenReturn(userDto);
        // Assuming createUser method saves User, Address, ContactDetails and EmergencyContact
        when(userRepository.save(any())).thenReturn(new User());
        when(addressRepository.save(any())).thenReturn(new Address());
        when(contactDetailsRepository.save(any())).thenReturn(new ContactDetails());
        when(emergencyContactRepository.save(any())).thenReturn(new EmergencyContact());
        // When
        UserDto result = underTest.createUser(userDto);
        // Then
        assertThat(result).isInstanceOf(UserDto.class);
        assertThat(result).isEqualTo(userDto);
        verify(userMapper).mapToEntity(any(UserDto.class));
        verify(addressMapper).mapToEntity(any());
        verify(addressRepository).save(any());
        verify(contactDetailsRepository).save(any());
        verify(emergencyContactRepository).save(any());
        verify(userRepository).save(any());
    }

    @Test
    void itShouldGetUsers() {
        //Given
        when(userRepository.findAll()).thenReturn(Collections.singletonList(new User()));
        when(userMapper.mapToDto(any())).thenReturn(userDto);
        //When
        List<UserDto> resultList = underTest.getUsers();
        //Then
        assertThat(resultList.size()).isEqualTo(1);
        assertThat(resultList.get(0)).isInstanceOf(UserDto.class);
        verify(userRepository).findAll();
        verify(userMapper).mapToDto(any());
    }

    @Test
    void itShouldGetUserById() {
        //Given
        when(userRepository.findById(any())).thenReturn(Optional.of(User.builder()
                        .userId(UUID.fromString("dec60e61-908b-446c-963a-4ac54a9f1ae0"))
                        .build()
        ));
        when(userMapper.mapToDto(any())).thenReturn(userDto);
        //When
        UserDto result = underTest.getUserById("dec60e61-908b-446c-963a-4ac54a9f1ae0");
        //Then
        assertThat(result).isInstanceOf(UserDto.class);
        assertThat(result).isEqualTo(userDto);
        verify(userRepository).findById(any(UUID.class));
        verify(userMapper).mapToDto(any(User.class));
    }

    @Test
    void itShouldThrowRunTimeExceptionIfNoUserWasFoundWithTheChosenId(){
        //given
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.getUserById("dec60e61-908b-446c-964a-4ac54a9f1ae0"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

}