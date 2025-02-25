package org.cosmic.cafe.application.member.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberListResponse {
    private UUID id;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;
    

    public MemberListResponse(UUID id, String name, String nickname, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
