package com.app.loan.controller;

import com.app.loan.dto.LoanDto;
import com.app.loan.exception.CustomerNotFoundException;
import com.app.loan.model.Customer;
import com.app.loan.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController(value = "/")
@Validated
public class LoanController {

  private final LoanService loanService;
  private final RestTemplate restTemplate;

  @Autowired
  public LoanController(LoanService loanService, RestTemplate restTemplate) {
    this.loanService = loanService;
    this.restTemplate = restTemplate;
  }

  @PostMapping(
          value = "api/loanapplications",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LoanDto> save(@RequestBody @Valid LoanDto loanDto) {

    Customer customer = restTemplate.getForObject(String.format("http://localhost:5555/api/customers/%s", loanDto.getCustomerId()), Customer.class);

    if (customer == null) {
      throw new CustomerNotFoundException(String.format("Customer with id %s doesn't exist!", loanDto.getCustomerId()));
    }

    return ResponseEntity
            .ok()
            .body(loanService.save(loanDto));
  }

  @GetMapping(
          value = "api/loanapplications/customers/{customerId}",
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LoanDto>> getLoansByCustomerId(@PathVariable String customerId) {

    Customer customer = restTemplate.getForObject(String.format("http://localhost:5555/api/customers/%s", customerId), Customer.class);

    if (customer == null) {
      throw new CustomerNotFoundException(String.format("Customer with id %s doesn't exist!", customerId));
    }

    return ResponseEntity
            .ok()
            .body(loanService.getLoansByCustomerId(customerId));
  }
}
