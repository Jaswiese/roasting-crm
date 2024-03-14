package dev.jasperwiese.roastingCRM.exceptions;

import dev.jasperwiese.roastingCRM.exceptions.client.ClientNotFoundException;
import dev.jasperwiese.roastingCRM.exceptions.roastingProfile.RoastingProfileNotFoundException;
import dev.jasperwiese.roastingCRM.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorObject> handleClientNotFoundException(ClientNotFoundException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUserNotFoundException(UserNotFoundException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject(
          HttpStatus.NOT_FOUND.value(),
          e.getMessage(),
          ZonedDateTime.now()
        );
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoastingProfileNotFoundException.class)
    public ResponseEntity<ErrorObject> handleRoastingProfileNotFoundException (RoastingProfileNotFoundException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
}
