package com.application.users.infrastructure.security;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.application.users.application.ports.TokenGateway;
import com.application.users.domain.UserId;
import com.application.users.domain.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class SecurityFilter extends OncePerRequestFilter{

    private final TokenGateway tokenGateway;
    private final UserRepository userRepository;


    public SecurityFilter(TokenGateway tokenGateway, UserRepository userRepository) {
        this.tokenGateway = tokenGateway;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token = recoverToken(request);
                if (token!=null){
                    String userId = tokenGateway.validateTokenAndGetUserID(token);
                    if(userId != null){
                        userRepository.findById(new UserId(UUID.fromString(userId))).ifPresent(user -> {
                            var authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        });
                    }
                }
                filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
    
}
