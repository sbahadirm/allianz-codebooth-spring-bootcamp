package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccAccountType;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccCurrencyType;
import lombok.Data;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccAccountSaveRequestDto {

    private Long cusCustomerId;
    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType accountType;
}
