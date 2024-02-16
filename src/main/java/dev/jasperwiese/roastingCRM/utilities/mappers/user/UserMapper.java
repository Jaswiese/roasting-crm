package dev.jasperwiese.roastingCRM.utilities.mappers.user;

import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.user.User;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactDetailsMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.EmergencyContactMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UserMapper {

    private final UserAddressMapper userAddressMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final EmergencyContactMapper emergencyContactMapper;
    private final AddressMapper addressMapper;

    public UserMapper(UserAddressMapper userAddressMapper, ContactDetailsMapper contactDetailsMapper, EmergencyContactMapper emergencyContactMapper, AddressMapper addressMapper) {
        this.contactDetailsMapper = contactDetailsMapper;
        this.userAddressMapper = userAddressMapper;
        this.emergencyContactMapper = emergencyContactMapper;
        this.addressMapper = addressMapper;
    }

    public User mapToEntity(UserDto userDto) {
        User user = new User();
        if(userDto.getUserId() != null && !userDto.getUserId().isEmpty()) {
            user.setUserId(UUID.fromString(userDto.getUserId()));
        }
        user.setVerified(userDto.getVerified());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPosition(userDto.getPosition());
        user.setRole(userDto.getRole());
        //user.setAddresses(userAddressMapper.mapUserDtoToUserAddressList(userDto));
        user.setContactDetails(contactDetailsMapper.mapToEntity(userDto.getContactDetailsDto()));
        user.setEmergencyContact(emergencyContactMapper.mapToEntity(userDto.getEmergencyContactDto()));
        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId().toString())
                .verified(user.getVerified())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .position(user.getPosition())
                .role(user.getRole())
                .contactDetailsDto(contactDetailsMapper.mapToDto(user.getContactDetails()))
                .emergencyContactDto(emergencyContactMapper.mapToDto(user.getEmergencyContact()))
                .build();
        userDto.setAddressDtoList(
               addressMapper.mapToAddressDtoListFromAddressList(
                       userAddressMapper.mapUserAddressListToAddressList(user.getAddresses())
               )
        );
        return userDto;
    }
}
