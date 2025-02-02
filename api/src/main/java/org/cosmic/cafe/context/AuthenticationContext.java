package org.cosmic.cafe.context;

import org.cosmic.cafe.dto.LoginPayload;

public class AuthenticationContext {
    private LoginPayload principal;

    public void setPrincipal(LoginPayload principal) {
        this.principal = principal;
    }
}
