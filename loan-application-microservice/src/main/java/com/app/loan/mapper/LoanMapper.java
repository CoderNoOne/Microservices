package com.app.loan.mapper;

import com.app.loan.model.Loan;
import com.app.loan.dto.LoanDto;
import org.mapstruct.Mapper;

@Mapper
public interface LoanMapper {

    Loan loanDtoToLoan(LoanDto loanDto);

    LoanDto loanToLoanDto(Loan loan);

}
