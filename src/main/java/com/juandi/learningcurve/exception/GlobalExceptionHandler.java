package com.juandi.learningcurve.exception;

import com.juandi.learningcurve.dto.ErrorResponse;
import com.juandi.learningcurve.dto.ValidationErrorResponse;
import com.juandi.learningcurve.util.RequestHelper;
import java.time.Instant;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrorResponse handleValidation(final MethodArgumentNotValidException ex) {

    final List<String> messages = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .distinct()
        .toList();

    return new ValidationErrorResponse(
        Instant.now(),
        HttpStatus.BAD_REQUEST.value(),
        "Validation Error",
        messages,
        RequestHelper.getPath()
    );
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFound(final NotFoundException ex) {
    return new ErrorResponse(
        Instant.now(),
        HttpStatus.NOT_FOUND.value(),
        "Not Found",
        ex.getMessage(),
        RequestHelper.getPath()
    );
  }

  @ExceptionHandler(DuplicateResourceException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handleDuplicateResource(final DuplicateResourceException ex) {
    return new ErrorResponse(
        Instant.now(),
        HttpStatus.CONFLICT.value(),
        "Conflict",
        ex.getMessage(),
        RequestHelper.getPath()
    );
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleGeneric(final Exception ex) {
    return new ErrorResponse(
        Instant.now(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Internal Server Error",
        ex.getMessage(),
        RequestHelper.getPath()
    );
  }
}