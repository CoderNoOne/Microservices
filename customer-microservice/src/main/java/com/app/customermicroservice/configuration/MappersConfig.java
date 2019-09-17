package com.app.customermicroservice.configuration;

import com.app.customermicroservice.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

  @Bean
  public CustomerMapper getCustomerMapper() {
    return Mappers.getMapper(CustomerMapper.class);
  }
}
