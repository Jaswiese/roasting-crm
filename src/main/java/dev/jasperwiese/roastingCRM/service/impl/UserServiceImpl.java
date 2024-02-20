package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.entity.user.User;
import dev.jasperwiese.roastingCRM.entity.user.UserAddress;
import dev.jasperwiese.roastingCRM.entity.user.UserAddressPK;
import dev.jasperwiese.roastingCRM.repository.AddressRepository;
import dev.jasperwiese.roastingCRM.repository.ContactDetailsRepository;
import dev.jasperwiese.roastingCRM.repository.EmergencyContactRepository;
import dev.jasperwiese.roastingCRM.repository.UserRepository;
import dev.jasperwiese.roastingCRM.service.UserService;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.user.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final EmergencyContactRepository emergencyContactRepository;
    private final ContactDetailsRepository contactDetailsRepository;



    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AddressMapper addressMapper, AddressRepository addressRepository, EmergencyContactRepository emergencyContactRepository, ContactDetailsRepository contactDetailsRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.emergencyContactRepository = emergencyContactRepository;
        this.contactDetailsRepository = contactDetailsRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());
        userDto.getContactDetailsDto().setContactDetailsId(UUID.randomUUID().toString());
        userDto.getEmergencyContactDto().setEmergencyContactId(UUID.randomUUID().toString());

        User user = userMapper.mapToEntity(userDto);
        //save contactDetails
        ContactDetails contactDetails = user.getContactDetails();
        contactDetails = contactDetailsRepository.save(contactDetails);
        user.setContactDetails(contactDetails);
        //save emergencyContact details
        EmergencyContact emergencyContact = user.getEmergencyContact();
        emergencyContact = emergencyContactRepository.save(emergencyContact);
        user.setContactDetails(contactDetails);
        //save addresses
        for (AddressDto addressDto : userDto.getAddressDtoList()) {

            addressDto.setAddressId(UUID.randomUUID().toString());
            Address address = addressMapper.mapToEntity(addressDto);

            address = addressRepository.save(address);

            UserAddress userAddress = new UserAddress();
            userAddress.setId(new UserAddressPK(user.getUserId(), address.getAddressId()));
            userAddress.setUser(user);
            userAddress.setAddress(address);
            user.getAddresses().add(userAddress);
        }
        user = userRepository.save(user);


        return userMapper.mapToDto(user);
    }
    @Transactional
    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public UserDto getUserById(String userId) {
        Optional<User> userOptional = userRepository.findById(UUID.fromString(userId));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.mapToDto(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
