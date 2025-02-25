package org.cosmic.cafe.infra.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cosmic.cafe.domain.member.Member;

@Entity
@Getter
@Table(name = "Member")
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String memberId;

    @Column(unique = true)
    private String nickname;
    private String password;
    private String role;
    private String avatar;
    private String phoneNumber;
    private String studentNumber;
    private LocalDateTime birth;
    private String email;

    @Version
    private Integer version;


    @Builder
    public MemberEntity(UUID id, String name, String memberId, String nickname, String password,
        String role, String avatar, String phoneNumber, String studentNumber, LocalDateTime birth,
        String email) {
        this.id = id;
        this.name = name;
        this.memberId = memberId;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
        this.birth = birth;
        this.email = email;
        this.version=0;
    }

    public static MemberEntity of(Member member) {
        return MemberEntity.builder()
            .id(member.getId())
            .name(member.getName())
            .memberId(member.getMemberId())
            .nickname(member.getNickname())
            .password(member.getPassword())
            .role(member.getRole())
            .avatar(member.getAvatar())
            .phoneNumber(member.getPhoneNumber())
            .studentNumber(member.getStudentNumber())
            .birth(member.getBirth())
            .email(member.getEmail())
            .build();
    }

}