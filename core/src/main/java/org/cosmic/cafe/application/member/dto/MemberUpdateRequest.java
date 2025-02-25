package org.cosmic.cafe.application.member.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequest {
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDateTime birth;
    private String email;
}
