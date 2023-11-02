/**
 * @author Bledar B
 */
package org.phones.rest.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * Handles exceptions
     *
     * @param e The thrown exception
     * @return a String response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorInfo errorInfo = new ErrorInfo(e);
        String respJSONstring = "{}";
        try {
            respJSONstring = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return ResponseEntity.badRequest().body(respJSONstring);
    }

    /**
     * Handles exception thrown by Validation
     *
     * @param e The thrown exception
     * @return a String response entity
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationException(Exception e) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorInfo errorInfo = new ErrorInfo(e);
        String respJSONstring = "{}";
        try {
            respJSONstring = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return ResponseEntity.badRequest().body(respJSONstring);
    }

    /**
     * Handles exception thrown by Validation
     *
     * @param e The thrown exception
     * @return a String response entity
     */
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> transactionSystemException(Exception e) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorInfo errorInfo = new ErrorInfo(e);
        String respJSONstring = "{}";
        try {
            respJSONstring = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return ResponseEntity.badRequest().body(respJSONstring);
    }

    /**
     * Handles exception thrown by Bean Validation on controller methods parameters
     *
     * @param e       The thrown exception
     * @param request the current web request
     * @return a String response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorInfo errorInfo = new ErrorInfo(e);
        String respJSONstring = "{}";
        try {
            respJSONstring = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return ResponseEntity.badRequest().body(respJSONstring);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class ErrorInfo {
        public final String type;
        public final String parameter;
        public final String errorMessage;

        public ErrorInfo(Exception ex) {
            if (ex instanceof MethodArgumentNotValidException) {
                type = "method.argument.not.valid";
                parameter = ((MethodArgumentNotValidException) ex).getFieldError().getField();
                errorMessage = ((MethodArgumentNotValidException) ex).getFieldError().getDefaultMessage();
            } else if (ex instanceof ValidationException) {
                type = "not.valid";
                parameter = null;
                errorMessage = ex.getLocalizedMessage();
            } else if (ex instanceof TransactionSystemException) {
                if (ex.getCause() instanceof RollbackException) {
                    if (ex.getCause().getCause() instanceof ConstraintViolationException) {
                        type = "validation.error";
                        parameter = ((ConstraintViolationException) ex.getCause().getCause()).getConstraintViolations().stream()
                            .map(ConstraintViolation::getPropertyPath)
                            .map(Path::toString)
                            .collect(Collectors.joining(" | "));
                        errorMessage = ((ConstraintViolationException) ex.getCause().getCause()).getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessageTemplate)
                            .collect(Collectors.joining(" | "));
                    } else {
                        type = "rollback.error";
                        parameter = null;
                        errorMessage = ex.getLocalizedMessage();
                    }
                } else {
                    type = "transaction.error";
                    parameter = null;
                    errorMessage = ex.getLocalizedMessage();
                }
            } else {
                type = "generic.error";
                parameter = null;
                errorMessage = ex.getLocalizedMessage();
            }
        }
    }
}
