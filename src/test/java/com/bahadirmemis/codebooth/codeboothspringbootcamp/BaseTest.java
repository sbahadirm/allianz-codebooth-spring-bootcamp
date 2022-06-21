package com.bahadirmemis.codebooth.codeboothspringbootcamp;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenRestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        GenRestResponse genRestResponse = getGenRestResponse(result);
        return genRestResponse.isSuccess();
    }

    protected GenRestResponse getGenRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        GenRestResponse genRestResponse = objectMapper.readValue(result.getResponse().getContentAsString(), GenRestResponse.class);
        return genRestResponse;
    }
}
