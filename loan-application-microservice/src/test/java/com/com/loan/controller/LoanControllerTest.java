package com.com.loan.controller;

import com.app.loan.controller.LoanController;
import com.app.loan.exception.CustomerNotFoundException;
import com.app.loan.dto.LoanDto;
import com.app.loan.model.Customer;
import com.app.loan.model.LoanStatus;
import com.app.loan.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("controllers")
@DisplayName("Test cases for loan controller")
@ExtendWith(SpringExtension.class)
class LoanControllerTest {

  private MockMvc mockMvc;

  private ObjectMapper mapper = new ObjectMapper();

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private LoanService loanService;

  @InjectMocks
  private LoanController loanController;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(loanController)
            .build();
  }

  @Test
  @DisplayName("api/loanapplications with customer not Found via RestTemplate should throw an exception with appropriate message")
  void test1() throws Exception {

    //given
    String url = "http://localhost:5555/api/customers/";

    LoanDto loanDto = LoanDto.builder()
            .status(LoanStatus.CREATED)
            .duration(2)
            .amount(2000.00)
            .customerId("2")
            .build();

    String expectedExceptionMessage = String.format("Customer with id %s doesn't exist!", loanDto.getCustomerId());

    given(restTemplate.getForObject(url + loanDto.getCustomerId(), Customer.class))
            .willReturn(null);

    //when
    //then
    mockMvc
            .perform(post("api/loanapplications"))
            .andExpect(status().isNotFound());

    CustomerNotFoundException actualException = assertThrows(CustomerNotFoundException.class, () -> loanController.save(loanDto));
    assertThat(actualException.getMessage(), is(equalTo(expectedExceptionMessage)));

    then(restTemplate).should(times(1)).getForObject(url + loanDto.getCustomerId(), Customer.class);
    then(loanService).should(never()).save(loanDto);
  }

  @Test
  @DisplayName("api/loanapplication with existing customer id")
  void test2() throws Exception {

    //given

    String json = "{\"amount\": 2000.00, \"duration\": 1, \"customerId\": \"2\"}";

    String expectedJson = "{\"amount\": 2000.00, \"duration\": 1, \"status\": \"IN_PROGRESS\", \"customerId\": \"2\"}";
    String url = "http://localhost:5555/api/customers/";

    LoanDto loanDto = LoanDto.builder()
            .status(LoanStatus.CREATED)
            .duration(1)
            .amount(2000.00)
            .customerId("2")
            .build();


    given(restTemplate.getForObject(url + loanDto.getCustomerId(), Customer.class))
            .willReturn(Customer.builder()
                    .id("2")
                    .city("city")
                    .address("address")
                    .firstName("firstName")
                    .lastName("lastName")
                    .phone("34234")
                    .postalCode("22222")
                    .build());

    given(loanService.save(any()))
            .willReturn(LoanDto.builder()
                    .id("2323")
                    .customerId(loanDto.getCustomerId())
                    .amount(loanDto.getAmount())
                    .duration(loanDto.getDuration())
                    .status(LoanStatus.IN_PROGRESS)
                    .build());

    //when
    mockMvc
            .perform(post("/api/loanapplications")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
            //then
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(content().json(expectedJson));
  }

  @Test
  @DisplayName("get loans for customer")
  void test3() throws Exception {

    //given
    String customerId = "10";

    Customer customer = Customer.builder()
            .id(customerId)
            .city("city")
            .address("address")
            .firstName("firstName")
            .lastName("lastName")
            .phone("34234")
            .postalCode("22222")
            .build();

    given(restTemplate.getForObject("http://localhost:5555/api/customers/" + customerId, Customer.class))
            .willReturn(customer);

    List<LoanDto> loans = Arrays.asList(
            LoanDto.builder()
                    .id("1")
                    .status(LoanStatus.IN_PROGRESS)
                    .duration(2)
                    .amount(2000.00)
                    .customerId(customerId)
                    .build(),

            LoanDto.builder()
                    .id("2")
                    .status(LoanStatus.IN_PROGRESS)
                    .duration(4)
                    .amount(1200.00)
                    .customerId(customerId)
                    .build()
    );

    String stringLoans = mapper.writeValueAsString(loans);

    given(loanService.getLoansByCustomerId(customerId))
            .willReturn(loans);

    given(restTemplate.getForObject("http://localhost:5555/api/customers/" + customerId, Customer.class))
            .willReturn(customer);

    //when
    mockMvc
            .perform(get("/api/loanapplications/customers/" + customerId))
            //then
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().json(stringLoans));

  }
}

