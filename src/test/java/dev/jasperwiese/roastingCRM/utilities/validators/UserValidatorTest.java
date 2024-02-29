package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.entity.user.User;
import dev.jasperwiese.roastingCRM.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    private UserValidator underTest;
    @Mock
    private UserRepository userRepository;
    private UUID userId;
    @BeforeEach
    void setUp() {
        underTest = new UserValidator(userRepository);
        userId = UUID.fromString("0abb9c79-4f9b-4eb2-9f5d-2b4d8baa0fdd");
    }
    @Test
    void itShouldValidateIfUserExists() {
        //Given
        User user = new User();
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        //When
        User result = underTest.validateIfUserExists(userId.toString());
        //Then
        assertThat(result).isInstanceOf(User.class);
    }

    @Test
    void itShouldThrowARuntimeExceptionIfUserDoesNotExist() {
        assertThatThrownBy(() -> underTest.validateIfUserExists(userId.toString()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User does not exist");

    }
}