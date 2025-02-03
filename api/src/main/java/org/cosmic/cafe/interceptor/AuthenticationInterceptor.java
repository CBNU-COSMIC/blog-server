package org.cosmic.cafe.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.context.AuthenticationContext;
import org.cosmic.cafe.dto.LoginPayload;
import org.cosmic.cafe.exception.type.ApiErrorCode;
import org.cosmic.cafe.exception.type.AuthorizationException;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final AuthenticationContext authenticationContext;
    private final List<Pattern> publicUrls = List.of(
            Pattern.compile("^/api/auth/sign-up$"),
            Pattern.compile("^/api/auth/sign-in$"),
            Pattern.compile("^/api/posts$"),
            Pattern.compile("^/api/schedules$")
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (shouldNotHandle(request)) {
            return true;
        }

        if (preProcessing(handler, request)) {
            return true;
        }

        HttpSession session = request.getSession();
        Object attribute = session.getAttribute("loginPayload");
        if (Objects.isNull(attribute)) {
            throw new AuthorizationException("사용자가 존재하지 않습니다.", ApiErrorCode.UNAUTHORIZED);
        }

        LoginPayload loginPayload = (LoginPayload) attribute;
        authenticationContext.setPrincipal(loginPayload);
        return true;
    }

    private boolean preProcessing(Object handler, HttpServletRequest request) {
        return handler instanceof ResourceHttpRequestHandler || CorsUtils.isPreFlightRequest(request);
    }

    private boolean shouldNotHandle(HttpServletRequest request) {
        return publicUrls.stream()
                .anyMatch(pattern -> pattern.matcher(request.getRequestURI()).matches());
    }
}
