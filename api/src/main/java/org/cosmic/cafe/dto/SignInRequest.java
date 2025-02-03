package org.cosmic.cafe.dto;

public record SignInRequest(
        String userId,
        String password
) {
}
