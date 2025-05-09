package org.cosmic.cafe.application.member.dto;

import java.time.LocalDateTime;

public record MemberCreationPayload(
    String name,
    String memberId,
    String nickname,
    String password,
    String role,
    String avatar,
    String phoneNumber,
    String studentNumber,
    LocalDateTime birth,
    String email
) {
}