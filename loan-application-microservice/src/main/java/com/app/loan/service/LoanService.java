package com.app.loan.service;

import com.app.loan.mapper.LoanMapper;
import com.app.loan.repository.LoanRepository;
import com.app.loan.dto.LoanDto;
import com.app.loan.model.LoanStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

  private final LoanRepository loanRepository;
  private final LoanMapper loanMapper /*= Mappers.getMapper(LoanMapper.class)*/;

  public List<LoanDto> getLoansByCustomerId(String id) {

    log.debug("get loans by customer id {}", id);
    return loanRepository
            .findLoansByCustomerId(id)
            .stream()
            .map(loanMapper::loanToLoanDto)
            .peek(loanDto -> loanDto.setStatus(LoanStatus.IN_PROGRESS))
            .collect(Collectors.toList());
  }

  public LoanDto save(LoanDto loanDto) {

    log.debug("save loan invoked");

    loanDto.setStatus(LoanStatus.CREATED);
    loanDto.setId(UUID.randomUUID().toString());

    LoanDto savedLoanDto = loanMapper.loanToLoanDto(loanRepository
            .save(loanMapper.loanDtoToLoan(loanDto)));

    log.debug("Saved loan with id: {}", loanDto.getId());

    return savedLoanDto;
  }
}
