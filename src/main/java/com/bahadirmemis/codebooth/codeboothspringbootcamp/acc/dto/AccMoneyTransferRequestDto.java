package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccMoneyTransferType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyTransferRequestDto {

    private Long accAccountIdFrom;
    private Long accAccountIdTo;
    private BigDecimal amount;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;
}
