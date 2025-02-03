package org.cosmic.cafe.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.context.AuthenticationContext;
import org.cosmic.cafe.controller.annotation.Admin;
import org.cosmic.cafe.domain.member.Role;
import org.cosmic.cafe.exception.ApiErrorCode;
import org.cosmic.cafe.exception.type.AuthorizationException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final AuthenticationContext authenticationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (shouldNotHandle((HandlerMethod) handler)) {
            return true;
        }

        Role role = authenticationContext.getPrincipal().role();
        if (role != Role.PRESIDENT) {
            throw new AuthorizationException("권한이 없습니다.", ApiErrorCode.FORBIDDEN);
        }
        return true;
    }

    private boolean shouldNotHandle(HandlerMethod handler) {
        HandlerMethod handlerMethod = handler;
        if (!handlerMethod.hasMethodAnnotation(Admin.class)) {
            return true;
        }
        return false;
    }
}
