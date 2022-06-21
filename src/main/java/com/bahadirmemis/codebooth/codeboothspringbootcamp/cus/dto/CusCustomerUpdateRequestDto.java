package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class CusCustomerUpdateRequestDto {

    private Long id;
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
