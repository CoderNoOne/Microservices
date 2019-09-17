package com.app.loan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(RuntimeException e) {

    log.info(e.getMessage());
    log.error(Arrays.toString(e.getStackTrace()));

    return new ResponseEntity<>(
            ErrorResponse.builder()
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .message("Customer not found")
                    .build(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NotValidLoanException.class)
  public ResponseEntity<ErrorResponse> handleNotValidLoanException(RuntimeException e) {

    log.info(e.getMessage());
    log.error(Arrays.toString(e.getStackTrace()));

    return new ResponseEntity<>(
            ErrorResponse.builder()
                    .errorCode(HttpStatus.BAD_REQUEST.value())
                    .message("Customer not found")
                    .build(), HttpStatus.BAD_REQUEST);

  }
}
