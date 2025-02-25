package org.cosmic.cafe.domain.member;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.member.exception.MemberErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.infra.member.entity.MemberEntity;

@Getter
public class Member {
    private final UUID id;
    private final String name;
    private final String memberId;
    private final String nickname;
    private final String password;
    private final String role;
    private final String avatar;
    private final String phoneNumber;
    private final String studentNumber;
    private final LocalDateTime birth;
    private final String email;

    public static final String ERROR_NAME_IS_REQUIRED = "이름은 필수 입력값입니다.";
    public static final String ERROR_NAME_INVALID_FORMAT = "이름은 영어나 한글만 사용할 수 있습니다.";
    public static final String ERROR_MEMBERID_IS_REQUIRED = "아이디는 필수 입력값입니다.";
    public static final String ERROR_MEMBERID_INVALID_FORMAT = "아이디는 영어 소문자와 숫자만 사용할 수 있습니다.";
    public static final String ERROR_PASSWORD_IS_REQUIRED = "비밀번호는 필수 입력값입니다.";
    public static final String ERROR_PASSWORD_LENGTH_REQUIRED = "비밀번호는 8자 이상 16자 이하여야 합니다.";
    public static final String ERROR_PASSWORD_INVALID_FORMAT = "비밀번호는 소문자, 대문자, 숫자, 기호를 모두 포함해야 합니다.";
    public static final String ERROR_STUDENTNUMBER_IS_REQUIRED = "학번은 필수 입력값입니다.";
    public static final String ERROR_STUDENTNUMBER_LENGTH_REQUIRED = "학번은 6~11자 사이의 숫자여야 합니다.";
    public static final String ERROR_EMAIL_IS_REQUIRED = "이메일은 필수 입력값입니다.";
    public static final String ERROR_EMAIL_INVALID_FORMAT = "이메일 형식에 맞게 작성해야 합니다.";
    public static final String ERROR_NICKNAME_IS_REQUIRED = "닉네임은 필수 입력값입니다.";
    public static final String ERROR_NICKNAME_INVALID_FORMAT = "닉네임은 문자만 가능합니다.";
    public static final String ERROR_NICKNAME_LENGTH_REQUIRED = "닉네임 길이는 10자 미만이어야 합니다.";


    @Builder
    public Member(UUID id, String name, String memberId, String nickname, String password,
        String role, String avatar, String phoneNumber, String studentNumber, LocalDateTime birth,
        String email) {

        validateName(name);
        validateMemberId(memberId);
        validatePassword(password);
        validateStudentNumber(studentNumber);
        validateEmail(email);
        validateNickname(nickname);

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
    }


    private void validateName(String name){
        if(name == null || name.isEmpty() || name.isBlank()){
            throw new BadRequestException(ERROR_NAME_IS_REQUIRED, MemberErrorCode.REQUIRED_VALUE);
        }
        if(!(name.matches("^[a-zA-Z]+$") || name.matches("^[가-힣]+$"))){
            throw new BadRequestException(ERROR_NAME_INVALID_FORMAT, MemberErrorCode.INVALID_NAME_FORMAT);
        }
    }

    private void validateMemberId(String memberId){
        if(memberId == null || memberId.isEmpty() || memberId.isBlank()){
            throw new BadRequestException(ERROR_MEMBERID_IS_REQUIRED, MemberErrorCode.REQUIRED_VALUE);
        }
        if(!(memberId.matches("^[a-z0-9]+$"))){
            throw new BadRequestException(ERROR_MEMBERID_INVALID_FORMAT, MemberErrorCode.INVALID_MEMBERID_FORMAT);
        }
    }

    private void validatePassword(String password){
        if(password == null || password.isEmpty() || password.isBlank()){
            throw new BadRequestException(ERROR_PASSWORD_IS_REQUIRED, MemberErrorCode.REQUIRED_VALUE);
        }
        if(password.length() < 8 || password.length() > 16){
            throw new BadRequestException(ERROR_PASSWORD_LENGTH_REQUIRED, MemberErrorCode.INVALID_PASSWORD_LENGTH);
        }
        if(!(password.matches(".*[a-z].*") &&
            password.matches(".*[A-Z].*") &&
            password.matches(".*\\d.*") &&
            password.matches(".*[!@#$%^&*()_+~`{}\\[\\]:;\"'<>,.?/\\\\|-].*"))){
            throw new BadRequestException(ERROR_PASSWORD_INVALID_FORMAT, MemberErrorCode.INVALID_PASSWORD_FORMAT);
        }
    }

    private void validateStudentNumber(String studentNumber){
        if(studentNumber == null || studentNumber.isEmpty() || studentNumber.isBlank()){
            throw new BadRequestException(ERROR_STUDENTNUMBER_IS_REQUIRED, MemberErrorCode.REQUIRED_VALUE);
        }
        if(studentNumber.length() < 6 || studentNumber.length() > 11){
            throw new BadRequestException(ERROR_STUDENTNUMBER_LENGTH_REQUIRED, MemberErrorCode.INVALID_STUDENTNUMBER_LENGTH);
        }
    }

    private void validateEmail(String email){
        if(email == null || email.isEmpty() || email.isBlank()){
            throw new BadRequestException(ERROR_EMAIL_IS_REQUIRED, MemberErrorCode.REQUIRED_VALUE);
        }
        if(!(email.matches("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))){
            throw new BadRequestException(ERROR_EMAIL_INVALID_FORMAT, MemberErrorCode.INVALID_EMAIL_FORMAT);
        }
    }

    private void validateNickname(String nickname){
        if(nickname == null || nickname.isEmpty() || nickname.isBlank()){
            throw new BadRequestException(ERROR_NICKNAME_IS_REQUIRED, MemberErrorCode.REQUIRED_VALUE);
        }
        if(nickname.length() > 9){
            throw new BadRequestException(ERROR_NICKNAME_LENGTH_REQUIRED, MemberErrorCode.INVALID_NICKNAME_LENGTH);
        }
        if(!(nickname.matches("^[a-zA-Z0-9가-힣\\s]+$"))){
            throw new BadRequestException(ERROR_NICKNAME_INVALID_FORMAT, MemberErrorCode.INVALID_NICKNAME_FORMAT);
        }
    }

    public static Member of(MemberEntity memberEntity) {
        return Member.builder()
            .id(memberEntity.getId())
            .name(memberEntity.getName())
            .memberId(memberEntity.getMemberId())
            .nickname(memberEntity.getNickname())
            .password(memberEntity.getPassword())
            .role(memberEntity.getRole())
            .avatar(memberEntity.getAvatar())
            .phoneNumber(memberEntity.getPhoneNumber())
            .studentNumber(memberEntity.getStudentNumber())
            .birth(memberEntity.getBirth())
            .email(memberEntity.getEmail())
            .build();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) { this.role = role; }
}