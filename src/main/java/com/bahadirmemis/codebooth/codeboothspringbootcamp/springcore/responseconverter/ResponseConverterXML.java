package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.responseconverter;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
//@Primary
//@Component
@Qualifier("xml")
public class ResponseConverterXML implements ResponseConverter{

    @Override
    public void convert(){
        System.out.println("XML");
    }
}
