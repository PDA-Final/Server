package com.pda.challengeapplication.configs.security;

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

        List<UserRole> userRoles = List.of(UserRole.NORMAL, UserRole.FINFLUENCER);

        matcherChain.add(
                SecurityRequestMatcher.permitAllOf(HttpMethod.GET,"/my-challenges"));
        matcherChain.add(
                SecurityRequestMatcher.permitAllOf(HttpMethod.GET,"/my-challenges/badge"));
//        matcherChain.add(
//                SecurityRequestMatcher.anyHasAnyRoles(userRoles, HttpMethod.POST, "/my-challenges"));
        matcherChain.add(
                SecurityRequestMatcher.hasAnyRolesOf(userRoles, "/my-challenges/**"));

        matcherChain.add(
                SecurityRequestMatcher.hasAnyRolesOf(userRoles, "/my-emoChallenges/**"));


        return matcherChain;
    }
}
