package com.app.loan.service;

import com.app.loan.dto.LoanDto;
import com.app.loan.mapper.LoanMapper;
import com.app.loan.model.Loan;
import com.app.loan.model.LoanStatus;
import com.app.loan.repository.LoanRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@Tag("services")
@DisplayName("Test cases for loan service")
@ExtendWith(SpringExtension.class)
class LoanServiceTest {

  @MockBean
  private LoanRepository loanRepository;

  private LoanService loanService;

  private static LoanMapper loanMapper;

  @BeforeAll
  static void setMapper() {
    loanMapper = Mappers.getMapper(LoanMapper.class);
  }

  @BeforeEach
  void setUp() {
    loanService = new LoanService(loanRepository, loanMapper);
  }

  @AfterEach
  void tearDown() {
    clearInvocations(loanRepository);
  }

  @Test
  @DisplayName("get Loans By customerId")
  void test1() {

    //given
    String customerId = "1";

    List<LoanDto> expectedResult = Collections.singletonList(
            LoanDto.builder()
                    .id("1")
                    .customerId(customerId)
                    .duration(4)
                    .amount(4000.00)
                    .status(LoanStatus.IN_PROGRESS)
                    .build());

    given(loanRepository.findLoansByCustomerId(customerId))
            .willReturn(Collections.singletonList(
                    Loan.builder()
                            .id("1")
                            .customerId(customerId)
                            .duration(4)
                            .amount(4000.00)
                            .status(LoanStatus.CREATED)
                            .build()));

    //when
    //then
    assertDoesNotThrow(() -> {
      List<LoanDto> actualResult = loanService.getLoansByCustomerId(customerId);
      assertThat(actualResult, is(equalTo(expectedResult)));
    });

    then(loanRepository)
            .should(times(1))
            .findLoansByCustomerId(customerId);
  }

  @Test
  @DisplayName("save Loan")
  void test2() {

    //given
    LoanDto loanDto = LoanDto.builder()
            .amount(2000.00)
            .duration(2)
            .customerId("1")
            .build();

    Loan expectedResult = Loan.builder()
            .amount(2000.00)
            .duration(2)
            .customerId("1")
            .status(LoanStatus.CREATED)
            .build();

    ArgumentCaptor<Loan> loanCaptor = ArgumentCaptor.forClass(Loan.class);
    given(loanRepository.save(any()))
            .willReturn(expectedResult);

    //when
    //then
    assertDoesNotThrow(() -> {
      LoanDto actualResult = loanService.save(loanDto);
      assertThat(actualResult, is(equalTo(loanMapper.loanToLoanDto(expectedResult))));
    });

    then(loanRepository).should(times(1)).save(loanCaptor.capture());
    assertThat(loanCaptor.getValue().getId(), is(not(nullValue())));
  }
}

