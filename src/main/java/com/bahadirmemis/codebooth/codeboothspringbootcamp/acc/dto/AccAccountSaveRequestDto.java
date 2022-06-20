package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccAccountType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccCurrencyType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccAccountSaveRequestDto {

    @NotNull
    @Positive
    private Long cusCustomerId;

    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType accountType;
}
