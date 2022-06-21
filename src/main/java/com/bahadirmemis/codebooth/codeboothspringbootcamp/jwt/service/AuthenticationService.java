package com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.service;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.service.CusCustomerService;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.dto.JwtLoginRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.enums.JwtConstant;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.security.JwtTokenGenerator;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CusCustomerService cusCustomerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public CusCustomerDto register(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return cusCustomerDto;
    }

    public String login(JwtLoginRequestDto jwtLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtLoginRequestDto.getIdentityNo().toString(),
                jwtLoginRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJWTToken(authentication);

        String bearerToken = JwtConstant.BEARER.getConstant() + token;

        return bearerToken;
    }

    public Long getCurrentCustomerId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = getJwtUserDetails(authentication);

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getJwtUserDetails(Authentication authentication) {
        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
