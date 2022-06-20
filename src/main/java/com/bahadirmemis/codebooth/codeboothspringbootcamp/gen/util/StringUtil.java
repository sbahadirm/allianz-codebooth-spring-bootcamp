package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.util;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.GenBusinessException;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class StringUtil {

    public static String getRandomNumberAsString(int charCount){

        if (charCount <= 0){
            throw new GenBusinessException(GenErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE);
        }

        return RandomStringUtils.randomNumeric(charCount);
    }
}
