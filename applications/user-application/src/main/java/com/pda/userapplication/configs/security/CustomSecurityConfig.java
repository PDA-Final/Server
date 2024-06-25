package com.pda.userapplication.configs.security;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcher;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcherChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;

@Configuration
public class CustomSecurityConfig {
    @Bean
    public SecurityRequestMatcherChain securityRequestMatcherChain() {
        SecurityRequestMatcherChain securityRequestMatcherChain = new SecurityRequestMatcherChain();

        securityRequestMatcherChain
            .add(SecurityRequestMatcher.hasRoleOf(UserRole.ADMIN, "/test"));
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.hasAnyRolesOf(List.of(UserRole.NORMAL, UserRole.FINFLUENCER),
                "/users/assets", "/users/public-options", "/users/detail-info",
                "/users/tendency", "/users/profile", "/users/accounts"));
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.hasAnyRolesOf(List.of(UserRole.NORMAL, UserRole.FINFLUENCER),
                HttpMethod.POST, "/users/{id:[0-9]+}/portfolios"));
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.authenticatedOf(HttpMethod.POST, "/users/{id:[0-9]+}/follow"));
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.authenticatedOf("/users/{id:[0-9]+}/portfolios"));
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.hasRoleOf(UserRole.NORMAL, HttpMethod.PUT, "/finfluencer"));
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.hasRoleOf(UserRole.FINFLUENCER, "/finfluencer/**"));

        return securityRequestMatcherChain;
    }
}
