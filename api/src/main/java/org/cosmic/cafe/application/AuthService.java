package org.cosmic.cafe.application;

import java.util.UUID;
import org.cosmic.cafe.domain.member.Role;
import org.cosmic.cafe.dto.LoginPayload;
import org.cosmic.cafe.dto.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public void signUp(SignUpRequest signUpRequest) {
    }

    public LoginPayload signIn(String userId, String password) {
        return new LoginPayload(UUID.randomUUID(), Role.GUEST);
    }
}
