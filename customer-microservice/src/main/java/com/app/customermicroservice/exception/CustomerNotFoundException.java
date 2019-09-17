package com.app.customermicroservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer doesn't exist")
public class CustomerNotFoundException extends RuntimeException {

  private String message;
}
