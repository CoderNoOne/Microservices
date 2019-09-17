package com.app.loan.dto;

import com.app.loan.model.LoanStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoanDto {

    private String id;
    @NotNull
    private Double amount;
    @NotNull
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    @NotNull
    private String customerId;
}
