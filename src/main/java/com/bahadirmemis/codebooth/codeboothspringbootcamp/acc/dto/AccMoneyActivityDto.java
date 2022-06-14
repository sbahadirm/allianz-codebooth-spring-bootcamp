package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.dto;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.EnumAccActivityType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class AccMoneyActivityDto {

    private AccAccount accAccount;
    private BigDecimal amount;
    private EnumAccActivityType activityType;
}
