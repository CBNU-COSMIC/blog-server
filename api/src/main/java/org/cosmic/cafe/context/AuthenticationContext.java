package org.cosmic.cafe.context;

import org.cosmic.cafe.dto.LoginPayload;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthenticationContext {
    private LoginPayload principal;

    public LoginPayload getPrincipal() {
        return principal;
    }

    public void setPrincipal(LoginPayload principal) {
        this.principal = principal;
    }
}
