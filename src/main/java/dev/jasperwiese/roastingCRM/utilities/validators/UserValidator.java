package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.entity.user.User;
import dev.jasperwiese.roastingCRM.exceptions.user.UserNotFoundException;
import dev.jasperwiese.roastingCRM.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateIfUserExists(String userId) {
        Optional<User> userOptional = userRepository.findById(
                UUID.fromString(userId));
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with ID: " + userId + " was not found.");
        }
        return userOptional.get();
    }
}
