package com.app.customermicroservice.controller;

import com.app.customermicroservice.dto.CustomerDto;
import com.app.customermicroservice.model.Customer;
import com.app.customermicroservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping(value = "api/customers"
          , consumes = MediaType.APPLICATION_JSON_VALUE
          , produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDto> save(
          @RequestBody @Valid CustomerDto customerDto) {

    log.debug("POST request to save Customer {}", customerDto);
    return ResponseEntity
            .ok()
            .body(customerService.saveCustomer(customerDto));

  }

  @GetMapping(value = "api/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {

    log.debug("GET request to get Customer : {}", id);
    return ResponseEntity
            .ok()
            .body(customerService.getCustomerById(id));
  }
}
