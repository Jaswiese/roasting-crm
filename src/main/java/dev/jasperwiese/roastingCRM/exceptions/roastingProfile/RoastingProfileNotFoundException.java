package dev.jasperwiese.roastingCRM.exceptions.roastingProfile;

public class RoastingProfileNotFoundException extends RuntimeException {
    RoastingProfileNotFoundException(String message) {
        super(message);
    }
}
