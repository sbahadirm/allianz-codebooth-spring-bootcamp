package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.responseconverter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Component
@Qualifier("json")
public class ResponseConverterJSON implements ResponseConverter{

    @Override
    public void convert(){
        System.out.println("JSON");
    }

}
