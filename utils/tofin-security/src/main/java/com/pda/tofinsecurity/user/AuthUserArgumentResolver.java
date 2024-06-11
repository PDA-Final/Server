package com.pda.tofinsecurity.user;

import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)webRequest.getNativeRequest();

        Optional<String> token = jwtProvider.resolveToken(httpServletRequest);

        if (token.isPresent() && jwtProvider.validateAccessToken(token.get()).equals(TokenStatus.VALID))
            return jwtProvider.parseAccessToken(token.get());

        return null;
    }
}
