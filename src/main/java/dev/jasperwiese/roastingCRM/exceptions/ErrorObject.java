package dev.jasperwiese.roastingCRM.exceptions;

import java.time.ZonedDateTime;

public record ErrorObject(
        Integer statusCode,
        String message,
        ZonedDateTime timestamp
        ){}
