package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccMoneyTransferType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyTransferRequestDto {

    @NotNull
    @Positive
    private Long accAccountIdFrom;

    @NotNull
    @Positive
    private Long accAccountIdTo;

    @NotNull
    @Positive
    private BigDecimal amount;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;
}
