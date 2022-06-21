package com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenRestResponse;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.dto.JwtLoginRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtLoginRequestDto jwtLoginRequestDto){

        String token = authenticationService.login(jwtLoginRequestDto);

        return ResponseEntity.ok(GenRestResponse.of(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = authenticationService.register(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(GenRestResponse.of(cusCustomerDto));

    }
}
