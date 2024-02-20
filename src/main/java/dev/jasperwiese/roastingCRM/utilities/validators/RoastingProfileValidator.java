package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import dev.jasperwiese.roastingCRM.repository.RoastingProfileRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RoastingProfileValidator {

    private RoastingProfileRepository roastingProfileRepository;

    public RoastingProfileValidator(RoastingProfileRepository roastingProfileRepository) {
        this.roastingProfileRepository = roastingProfileRepository;
    }

    public RoastingProfile validateIfRoastingProfileExists(String roastingProfileId) {

        Optional<RoastingProfile> roastingProfileOptional = roastingProfileRepository.findById(
                UUID.fromString(roastingProfileId));
        if(roastingProfileOptional.isEmpty()) {
            throw new RuntimeException("Roasting profile not found");
        }
        return roastingProfileOptional.get();
    }
}
