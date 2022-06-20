package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.enums;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.BaseErrorMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum CusErrorMessage implements BaseErrorMessage {

    CUSTOMER_DOES_NOT_EXIST("Customer does not exist!")
    ;

    private String message;

    CusErrorMessage(String message) {
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
