package com.pda.tofinsecurity;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.filters.JwtAuthorizationFilter;
import com.pda.tofinsecurity.handlers.CustomAccessDeniedHandler;
import com.pda.tofinsecurity.handlers.CustomAuthenticationEntryPoint;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcherHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TofinSecurityConfiguration {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final SecurityRequestMatcherHelper requestMatcherHelper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(AbstractHttpConfigurer::disable)
            .addFilterBefore(
                jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(requestMatcherHelper::setAuthorizedRequest)
            .exceptionHandling(securityExceptionHandler->
                securityExceptionHandler
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(customAuthenticationEntryPoint));

        return http.build();
    }

    private String[] allAuthorities() {
        String[] authorities = new String[UserRole.values().length];
        int i = 0;

        for (UserRole role : UserRole.values())
            authorities[i++] = role.name();

        return authorities;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
