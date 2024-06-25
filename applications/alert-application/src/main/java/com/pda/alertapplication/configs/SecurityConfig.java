package com.pda.alertapplication.configs;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcher;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcherChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityRequestMatcherChain securityRequestMatcherChain() {
        SecurityRequestMatcherChain matcherChain = new SecurityRequestMatcherChain();
        matcherChain
                .add(SecurityRequestMatcher.authenticatedOf("/alerts/**"));

        return matcherChain;
    }
}