package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class CusCustomerSaveRequestDto implements Serializable {

    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
