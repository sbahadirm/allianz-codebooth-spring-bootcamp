package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.enums;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.BaseErrorMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum AccErrorMessage implements BaseErrorMessage {

    INSUFFICIENT_BALANCE("Insufficient Balance!"),
    CURRENCY_TYPES_MUST_BE_SAME("Currency types must be same!")
    ;

    private String message;

    AccErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
