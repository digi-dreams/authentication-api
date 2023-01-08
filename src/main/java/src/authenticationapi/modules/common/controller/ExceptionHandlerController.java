package src.authenticationapi.modules.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import src.authenticationapi.modules.common.exception.*;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public MessageException notFoundError(NotFoundException ex) {
        return new MessageException(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<MessageException> argumentValidationError(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage().toLowerCase().contains("field")
                        ? new MessageException(e.getDefaultMessage())
                        : new MessageException(e.getField(), "The field " + e.getField() + " " + e.getDefaultMessage())
                ).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public MessageException fieldMissing(ConstraintViolationException ex) {
        return new MessageException(ex.getConstraintViolations().stream()
                .map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                .collect(Collectors.toList()).toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public MessageException validationError(ValidationException ex) {
        return new MessageException(ex.getMessage());
    }
}
