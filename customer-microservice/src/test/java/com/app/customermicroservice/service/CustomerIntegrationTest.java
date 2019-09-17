package com.app.customermicroservice.service;

import com.app.customermicroservice.CustomerMicroserviceApplication;
import com.app.customermicroservice.controller.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("integration")
@DisplayName("add Customer integration test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerMicroserviceApplication.class)
class CustomerIntegrationTest {

  private MockMvc mockMvc;

  @Autowired
  private CustomerController customerController;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
            .standaloneSetup(customerController)
            .build();
  }

  @DisplayName("send api request and validate response (headers + content)")
  @Test
  void test1() throws Exception {

    //given
    String customerJsonToSave = "{  \"firstName\": \"John\",  \"lastName\": \"Doe\",  \"email\": \"johndoe@example.com\",  \"phone\": \"+49123456789\",  \"address\": \"Karl Lenin Allee 1\",  \"city\": \"Berlin\",  \"postalCode\": \"10000\" }\n";

    //when
    mockMvc
            .perform(post("/api/customers/")
                    .contentType("application/json")
                    .content(customerJsonToSave))
            //then
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$.id", is(notNullValue())))
            .andExpect(jsonPath("$.firstName", is(equalTo("John"))))
            .andExpect(jsonPath("$.lastName", is(equalTo("Doe"))))
            .andExpect(jsonPath("$.phone", is(equalTo("+49123456789"))))
            .andExpect(jsonPath("$.city").value(is(equalTo("Berlin"))))
            .andExpect(jsonPath("$.postalCode", is(equalTo("10000"))))
            .andExpect(jsonPath("$.address", is(equalTo("Karl Lenin Allee 1"))))
            .andExpect(jsonPath("$.email", is(equalTo("johndoe@example.com"))));

  }

}
