package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.validator;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums.AccErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.GenBusinessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class AccValidator {

    public void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new GenBusinessException(AccErrorMessage.INSUFFICIENT_BALANCE);
        }
    }

    public void validateCurrencyTypes(AccAccount accAccountFrom, AccAccount accAccountTo) {
        if (!accAccountFrom.getCurrencyType().equals(accAccountTo.getCurrencyType())){
            throw new GenBusinessException(AccErrorMessage.CURRENCY_TYPES_MUST_BE_SAME);
        }
    }
}
