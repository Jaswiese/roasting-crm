package dev.jasperwiese.roastingCRM.controller.user;

import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserControllerV1 {
    private final UserServiceImpl userService;

    public UserControllerV1(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping("user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
