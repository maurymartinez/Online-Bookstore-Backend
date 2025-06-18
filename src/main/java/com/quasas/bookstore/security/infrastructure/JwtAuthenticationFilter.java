package com.quasas.bookstore.security.infrastructure;

import com.quasas.bookstore.security.application.service.JwtService;
import com.quasas.bookstore.security.domain.User;
import com.quasas.bookstore.security.domain.UserRepository;
import com.quasas.bookstore.security.domain.valueobject.Email;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX= "Bearer ";

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHENTICATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(TOKEN_PREFIX.length());
        String username = jwtService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            userRepository.findByEmail(new Email(username))
                    .ifPresent(this::authenticate);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(User user) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, null, null
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
