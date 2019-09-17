package com.app.customermicroservice;

import com.app.customermicroservice.configuration.MappersConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MappersConfig.class)
@EnableEurekaClient
public class CustomerMicroserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerMicroserviceApplication.class, args);
  }
}
