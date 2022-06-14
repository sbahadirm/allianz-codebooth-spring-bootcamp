package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccMoneyTransferType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyTransferDto {

    private Long id;
    private Long accAccountIdFrom;
    private Long accAccountIdTo;
    private BigDecimal amount;
    private Date transactionDate;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;
}
