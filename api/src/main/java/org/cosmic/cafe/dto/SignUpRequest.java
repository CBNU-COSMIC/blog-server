package org.cosmic.cafe.dto;

import java.time.LocalDateTime;

public record SignUpRequest(
        String userId,
        String nickname,
        String password,
        String email,
        String username,
        LocalDateTime birth,
        String phone
) {
}