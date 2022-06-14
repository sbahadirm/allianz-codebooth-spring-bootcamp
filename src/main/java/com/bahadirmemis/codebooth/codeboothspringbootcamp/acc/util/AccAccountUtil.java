package com.bahadirmemis.codebooth.codeboothspringbootcamp.acc.util;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.util.StringUtil;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class AccAccountUtil {

    public static String getIbanNo() {

        String randomNumberAsString = StringUtil.getRandomNumberAsString(24);

        return "TR" + randomNumberAsString;
    }
}
