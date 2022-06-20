package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.util;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
class StringUtilTest {

    @Test
    void shouldGetRandomNumberAsString() {

        int charCount = 5;

        String string = StringUtil.getRandomNumberAsString(charCount);

        assertEquals(charCount, string.length());
    }

    @Test
    void shouldGetRandomNumberAsStringAllCharsAreNumeric() {

        int charCount = 5;

        String string = StringUtil.getRandomNumberAsString(charCount);

        boolean hasChar = false;
        for (char eachChar : string.toCharArray()) {
            boolean isDigit = Character.isDigit(eachChar);
            if (!isDigit){
                hasChar = true;
                break;
            }
        }

        assertFalse(hasChar);
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsZero(){

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> StringUtil.getRandomNumberAsString(0));

        assertEquals(GenErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE, genBusinessException.getBaseErrorMessage());
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsNegative(){

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> StringUtil.getRandomNumberAsString(-1));

        assertEquals(GenErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE, genBusinessException.getBaseErrorMessage());
    }
}