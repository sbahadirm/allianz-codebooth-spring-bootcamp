package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccAccountType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccCurrencyType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.EnumGenStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccAccountDto {

    private Long id;
    private Long cusCustomerId;
    private String ibanNo;
    private BigDecimal currentBalance;
    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType accountType;
    private EnumGenStatus status;
    private Date cancelDate;
}
