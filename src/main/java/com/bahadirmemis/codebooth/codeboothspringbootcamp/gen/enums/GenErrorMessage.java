package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.BaseErrorMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum GenErrorMessage implements BaseErrorMessage {

    CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE("Char count cannot be zero or negative!"),
    ITEM_NOT_FOUND("Item not found!"),
    PARAMETER_CANNOT_BE_NULL("Parameter cannot be null"),
    ;

    private String message;

    GenErrorMessage(String message) {
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
