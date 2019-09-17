package com.com.loan.repository;

import com.app.loan.model.Loan;
import com.app.loan.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Tag("repositories")
@DisplayName("Test cases for loan repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class LoanRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private LoanRepository loanRepository;

  /*just for the sake of testing repository in Spring Boot - actually it's testing jpaRepository and there's no need to test jpaRepository methods*/
  @Test
  @DisplayName("whenFindByCustomerId_thenReturnLoansForHim")
  void test1() {

    //given
    String customerId = "1";

    List<Loan> expectedResult = Arrays.asList(
            Loan.builder()
                    .id("1")
                    .amount(2000.00)
                    .duration(2)
                    .customerId(customerId)
                    .build(),

            Loan.builder()
                    .id("2")
                    .amount(3500.00)
                    .duration(4)
                    .customerId(customerId)
                    .build()
    );
    entityManager
            .persist(Loan.builder()
                    .id("1")
                    .amount(2000.00)
                    .duration(2)
                    .customerId(customerId)
                    .build());

    entityManager
            .persist(Loan.builder()
                    .id("2")
                    .amount(3500.00)
                    .duration(4)
                    .customerId(customerId)
                    .build());

    //when
    //then
    Assertions.assertDoesNotThrow(() -> {
      List<Loan> actualResult = loanRepository.findLoansByCustomerId(customerId);
      assertThat(actualResult, is(equalTo(expectedResult)));
    });

  }

}

