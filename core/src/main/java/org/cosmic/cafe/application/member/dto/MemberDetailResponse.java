package org.cosmic.cafe.application.member.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.member.Member;

@Getter
@Builder
public class MemberDetailResponse {
    private UUID id;
    private String memberId;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String role;
    private String avatar;
    private LocalDateTime birth;
    private String studentNumber;

    public static MemberDetailResponse of(Member member) {
        return MemberDetailResponse.builder()
            .id(member.getId())
            .memberId(member.getMemberId())
            .name(member.getName())
            .nickname(member.getNickname())
            .email(member.getEmail())
            .phoneNumber(member.getPhoneNumber())
            .role(member.getRole())
            .avatar(member.getAvatar())
            .birth(member.getBirth())
            .studentNumber(member.getStudentNumber())
            .build();
    }
}
