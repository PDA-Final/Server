package com.pda.creditapplication.configs;

import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcher;
import com.pda.tofinsecurity.hooks.SecurityRequestMatcherChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityRequestMatcherChain securityRequestMatcherChain() {
        SecurityRequestMatcherChain securityRequestMatcherChain = new SecurityRequestMatcherChain();
        securityRequestMatcherChain
            .add(SecurityRequestMatcher.hasAnyRolesOf(
                List.of(UserRole.NORMAL, UserRole.CORP, UserRole.FINFLUENCER),
                "/credit/withdraw"));
        return securityRequestMatcherChain;
    }
}
