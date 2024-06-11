package com.pda.tofinsecurity.filters;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenStatus;
import com.pda.tofinsecurity.user.AuthUserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("call filter {}", request.getRequestURI());
        Optional<String> token = jwtProvider.resolveToken(request);

        if (isValidAccessToken(request, token))
            setAuthentication(token.get());

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        AuthUserInfo authUserInfo = jwtProvider.parseAccessToken(token);

        UserDetails principal = new User(authUserInfo.getId().toString(),"", toGrantedAuthorities(authUserInfo.getUserRole()));

        SecurityContextHolder.getContext()
            .setAuthentication(new UsernamePasswordAuthenticationToken(principal, "",
                toGrantedAuthorities(authUserInfo.getUserRole())));
    }

    private boolean isValidAccessToken(HttpServletRequest request, Optional<String> token) {
        return !request.getRequestURI().equals("/reissue")
            && token.isPresent()
            && jwtProvider.validateAccessToken(token.get()).equals(TokenStatus.VALID);
    }

    private List<GrantedAuthority> toGrantedAuthorities(UserRole role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}
