package com.api.store.middlewares;


import com.api.store.utils.encryption.JwtTokenUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class JwtMiddlewareValidator extends OncePerRequestFilter {
    public final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtMiddlewareValidator(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        boolean isSwaggerApi = requestUri.startsWith("/api-docs");
        boolean isSwaggerUi = requestUri.startsWith("/swagger-ui");

        if (isSwaggerApi || isSwaggerUi) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = request.getHeader("Authorization");
        try {
            Map<String, Claim> claims = this.jwtTokenUtil.getTokenClaims(accessToken);
            String userId = this.jwtTokenUtil.getTokenSubject(accessToken);
            List<String> roles = claims.get("roles").asList(String.class);
            request.setAttribute("userId", userId);
            request.setAttribute("roles", roles);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        }
    }
}
