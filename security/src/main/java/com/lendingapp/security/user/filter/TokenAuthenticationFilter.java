package com.lendingapp.security.user.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static String BEARER = "Bearer";

    protected TokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final String authToken = request.getHeader(AUTHORIZATION);
        final String jwtToken = Optional.ofNullable(authToken)
                .map(token-> StringUtils.removeStart(token, BEARER))
                .map(String::trim)
                .orElseThrow(()-> new BadCredentialsException("Missing Authentication Token"));
        final Authentication auth = new UsernamePasswordAuthenticationToken(jwtToken,jwtToken);
        return getAuthenticationManager().authenticate(auth);
    }
}
