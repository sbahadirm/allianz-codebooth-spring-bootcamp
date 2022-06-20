package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenExceptionResponse;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenRestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@ControllerAdvice
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleGenBusinessExceptions(GenBusinessException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, detail, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> getResponseEntity(Date errorDate, String message, String detail, HttpStatus status) {
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detail);

        GenRestResponse<GenExceptionResponse> restResponse = GenRestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, status);
    }

}