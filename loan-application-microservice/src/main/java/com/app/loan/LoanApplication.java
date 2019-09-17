package com.app.loan;

import com.app.loan.configuration.BeansConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(BeansConfig.class)
@EnableEurekaClient
public class LoanApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoanApplication.class, args);
  }
}
