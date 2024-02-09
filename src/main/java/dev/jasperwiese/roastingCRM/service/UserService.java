package dev.jasperwiese.roastingCRM.service;

import dev.jasperwiese.roastingCRM.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getUsers();

    UserDto getUserById(String userId);

}
