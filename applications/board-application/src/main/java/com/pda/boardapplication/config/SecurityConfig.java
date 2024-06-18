package com.pda.boardapplication.config;

import com.pda.tofinsecurity.hooks.SecurityRequestMatcher;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcherChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityRequestMatcherChain securityRequestMatcherChain() {
        SecurityRequestMatcherChain securityRequestMatcherChain = new SecurityRequestMatcherChain();

        securityRequestMatcherChain
                .add(SecurityRequestMatcher.permitAllOf(HttpMethod.GET, "/boards/**"));
        securityRequestMatcherChain
                .add(SecurityRequestMatcher.authenticatedOf(HttpMethod.POST, "/boards/**"));
        securityRequestMatcherChain
                .add(SecurityRequestMatcher.authenticatedOf(HttpMethod.PUT, "/boards/**"));
        securityRequestMatcherChain
                .add(SecurityRequestMatcher.authenticatedOf(HttpMethod.DELETE, "/boards/**"));


        return securityRequestMatcherChain;
    }
}