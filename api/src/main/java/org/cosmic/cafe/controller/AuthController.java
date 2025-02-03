package org.cosmic.cafe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.AuthService;
import org.cosmic.cafe.dto.LoginPayload;
import org.cosmic.cafe.dto.SignInRequest;
import org.cosmic.cafe.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest, HttpSession session) {
        LoginPayload loginPayload = authService.signIn(signInRequest.userId(), signInRequest.password());
        session.setAttribute("loginPayload", loginPayload);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}

