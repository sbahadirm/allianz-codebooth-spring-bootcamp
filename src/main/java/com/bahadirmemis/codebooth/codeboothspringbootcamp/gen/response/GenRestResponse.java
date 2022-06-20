package com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class GenRestResponse<T> implements Serializable {

    private T data;
    private Date responseDate;
    private boolean isSuccess;
    private String messages;

    public GenRestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.responseDate = new Date();
    }

    public static <T> GenRestResponse<T> of(T t){
        return new GenRestResponse<>(t, true);
    }

    public static <T> GenRestResponse<T> error(T t){
        return new GenRestResponse<>(t, false);
    }

    public static <T> GenRestResponse<T> empty(){
        return new GenRestResponse<>(null, true);
    }

}
