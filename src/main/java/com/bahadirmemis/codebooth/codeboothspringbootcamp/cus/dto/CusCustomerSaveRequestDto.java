package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class CusCustomerSaveRequestDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Positive
    private Long identityNo;

    @Min(8)
    private String password;
}
