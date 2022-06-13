package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.responseconverter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class WebService {

    private final ResponseConverter responseConverter;

    public void convertResponse() {
        responseConverter.convert();
    }
}
