package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.exceptions;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenExceptionResponse;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenRestResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String message = "Validation failed!";
        String detail = getDetails(ex);

        return getResponseEntity(errorDate, message, detail, HttpStatus.BAD_REQUEST);

    }

    private String getDetails(MethodArgumentNotValidException ex) {
        String detail = "";
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        for (ObjectError objectError : objectErrorList) {

            String eachDetail = getEachDetail(objectErrorList, objectError);

            detail = detail + eachDetail;
        }
        return detail;
    }

    private String getEachDetail(List<ObjectError> objectErrorList, ObjectError objectError) {
        int index = getIndexAsNumber(objectErrorList, objectError);
        String fieldName = getFieldName(objectError.getCodes());

        String eachDetail = index + ". ";
        eachDetail = eachDetail + fieldName + " " + objectError.getDefaultMessage() + "  ";
        return eachDetail;
    }

    private int getIndexAsNumber(List<ObjectError> objectErrorList, ObjectError objectError) {
        int i = objectErrorList.indexOf(objectError) +1;
        return i;
    }

    private String getFieldName(String[] codes) {
        String fieldName = "";
        if (codes != null && codes.length > 0){
            fieldName = codes[0];
        }
        return fieldName;
    }

    private ResponseEntity<Object> getResponseEntity(Date errorDate, String message, String detail, HttpStatus status) {
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, detail);

        GenRestResponse<GenExceptionResponse> restResponse = GenRestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, status);
    }
}
