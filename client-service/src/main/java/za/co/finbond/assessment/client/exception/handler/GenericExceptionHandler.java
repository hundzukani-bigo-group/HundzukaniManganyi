package za.co.finbond.assessment.client.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import za.co.finbond.assessment.client.exception.AlreadyExistException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestControllerAdvice
@Configuration
public class GenericExceptionHandler {



    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<Error>> handle(MethodArgumentNotValidException exception) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(this.buildErrorResponse(exception.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<String> handle(HttpMessageNotReadableException exception) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(exception.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<Error>> handle(ConstraintViolationException exception) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(buildErrorResponse(exception.getConstraintViolations()));
    }

    @ExceptionHandler({WebExchangeBindException.class})
    public ResponseEntity<List<Error>> handle(WebExchangeBindException exception) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(buildErrorResponse(exception.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handle(RuntimeException exception) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<String> handle(AlreadyExistException exception) {
        log.error("", exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handle(Exception exception) {
        log.error("", exception);
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

    private List<Error> buildErrorResponse(List<FieldError> fieldErrors) {
        List<Error> errors = new ArrayList<>();
        fieldErrors.parallelStream().forEach(violation ->
                errors.add(Error
                        .builder()
                        .message(violation.getDefaultMessage())
                        .field(violation.getField())
                        .rejectedValue(null != violation.getRejectedValue() ? violation.getRejectedValue().toString() : null)
                        .build())
        );
        return errors;
    }

    public static List<Error> buildErrorResponse(Set<ConstraintViolation<?>> violations) {
        List<Error> errors = new ArrayList<>();
        violations.parallelStream().forEach(violation ->
                errors.add(Error.builder()
                        .field(null != violation.getPropertyPath() ? violation.getPropertyPath().toString() : null)
                        .message(null != violation.getMessage() ? violation.getMessage() : null)
                        .rejectedValue(null != violation.getInvalidValue() ? violation.getInvalidValue().toString() : null)
                        .build())
        );
        String error = errors.toString();
        log.error(error);
        return errors;
    }
}
