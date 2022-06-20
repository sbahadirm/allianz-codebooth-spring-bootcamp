package com.bahadirmemis.codebooth.codeboothspringbootcamp.transactional.util;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.entity.CusCustomer;
import org.springframework.util.StringUtils;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class TransactionUtil {

    public static CusCustomer getCustomer(String suffix){

        String name = "test";
        if (StringUtils.hasText(suffix)){
            name = name + "-" + suffix;
        }

        CusCustomer cusCustomer = new CusCustomer();
        cusCustomer.setName(name);
        cusCustomer.setSurname(name);
        cusCustomer.setIdentityNo(10000000000L);
        cusCustomer.setPassword("testtesttest");

        return cusCustomer;
    }
}
