package com.app.loan.configuration;

import com.app.loan.mapper.LoanMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeansConfig {

  @Bean
  public LoanMapper createLoanMapper() {
    return Mappers.getMapper(LoanMapper.class);
  }

  @Bean
  public RestTemplate createRestTemplate(){
    return new RestTemplate();
  }

  @Bean
  public WebClient.Builder createWebClientBuilder() {
    return WebClient.builder();
  }

}
