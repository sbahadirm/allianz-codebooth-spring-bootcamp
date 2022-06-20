package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyActivityRequestDto {

    @NotNull
    @Positive
    private Long accAccountId;

    @NotNull
    @Positive
    private BigDecimal amount;
}
