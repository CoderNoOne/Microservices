package com.app.customermicroservice.service;

import com.app.customermicroservice.dto.CustomerDto;
import com.app.customermicroservice.exception.CustomerNotFoundException;
import com.app.customermicroservice.mapper.CustomerMapper;
import com.app.customermicroservice.model.Customer;
import com.app.customermicroservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public CustomerDto saveCustomer(CustomerDto customerDto) {

    log.debug("save customer invoked");

    customerDto.setId(UUID.randomUUID().toString());

    CustomerDto savedCustomerDto = customerMapper.customerToCustomerDto(
            customerRepository
                    .save(customerMapper.customerDtoToCustomer(customerDto)));

    log.debug("Saved customer with id : {}", savedCustomerDto.getId());

    return savedCustomerDto;
  }

  public Customer getCustomerById(String id) {

    log.debug("get customer by id {}", id);
    return customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " doesn't exist"));
  }
}
