package com.app.customermicroservice.mapper;

import com.app.customermicroservice.dto.CustomerDto;
import com.app.customermicroservice.model.Customer;
import org.mapstruct.*;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);
}
