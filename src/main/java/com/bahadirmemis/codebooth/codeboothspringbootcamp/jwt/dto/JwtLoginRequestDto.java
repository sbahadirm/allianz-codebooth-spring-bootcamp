package com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.dto;

import lombok.Data;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class JwtLoginRequestDto {

    private Long identityNo;
    private String password;
}
