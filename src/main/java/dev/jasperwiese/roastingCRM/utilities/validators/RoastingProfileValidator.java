package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import dev.jasperwiese.roastingCRM.repository.RoastingProfileRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoastingProfileValidator {

    private final RoastingProfileRepository roastingProfileRepository;

    public RoastingProfileValidator(RoastingProfileRepository roastingProfileRepository) {
        this.roastingProfileRepository = roastingProfileRepository;
    }

    public boolean validateIfRoastingProfileExists(String roastingProfileId) {
        return roastingProfileRepository.existsById(UUID.fromString(roastingProfileId));
    }
}
