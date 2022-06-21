package com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.security;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.jwt.enums.JwtConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String fullToken = request.getHeader("Authorization");

        String token = getToken(fullToken);

        if (StringUtils.hasText(token)){

            boolean isValid = jwtTokenGenerator.validateToken(token);

            if (isValid){

                Long userIdByToken = jwtTokenGenerator.findUserIdByToken(token);
                UserDetails userDetails = jwtUserDetailsService.loadUserByUserId(userIdByToken);

                if (userDetails != null){

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(String fullToken) {
        String token = null;
        if (StringUtils.hasText(fullToken)){

            String bearerConstant = JwtConstant.BEARER.getConstant();

            if (fullToken.startsWith(bearerConstant)){

                token = fullToken.substring(bearerConstant.length());
            }
        }
        return token;
    }
}
