package org.cosmic.cafe.domain.member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MemberTest {

    @Nested
    class Member는 {

        @Test
        void 올바른_인자가_들어오면_객체가_생성된다() {
            // given
            UUID id = UUID.randomUUID();
            String name = "송영은";
            String memberId = "user123";
            String nickname = "영은";
            String password = "Password123!";
            String role = "USER";
            String phoneNumber = "010-1234-5678";
            String studentNumber = "20251234";
            LocalDateTime birth = LocalDateTime.of(1900, 1, 1, 0, 0);
            String email = "user@example.com";

            // when
            Member member = Member.builder()
                .id(id)
                .name(name)
                .memberId(memberId)
                .nickname(nickname)
                .password(password)
                .role(role)
                .phoneNumber(phoneNumber)
                .studentNumber(studentNumber)
                .birth(birth)
                .email(email)
                .build();

            // then
            assertThat(member.getId()).isEqualTo(id);
            assertThat(member.getName()).isEqualTo(name);
            assertThat(member.getMemberId()).isEqualTo(memberId);
            assertThat(member.getNickname()).isEqualTo(nickname);
            assertThat(member.getPassword()).isEqualTo(password);
            assertThat(member.getRole()).isEqualTo(role);
            assertThat(member.getPhoneNumber()).isEqualTo(phoneNumber);
            assertThat(member.getStudentNumber()).isEqualTo(studentNumber);
            assertThat(member.getBirth()).isEqualTo(birth);
            assertThat(member.getEmail()).isEqualTo(email);
        }

        @ParameterizedTest
        @MethodSource("generateInvalidArguments")
        void 사용자정보가_이름_아이디_비밀번호_학번_이메일_닉네임을_모두_포함하지_않으면_예외를_발생시킨다(String name, String memberId, String password, String email, String studentNumber, String nickname) {
            // given
            UUID id = UUID.randomUUID();

            // expect
            org.junit.jupiter.api.Assertions.assertThrows(BadRequestException.class, () ->
                Member.builder()
                    .id(id)
                    .name(name)
                    .memberId(memberId)
                    .password(password)
                    .email(email)
                    .studentNumber(studentNumber)
                    .nickname(nickname)
                    .build()
            );
        }

        private static Stream<Arguments> generateInvalidArguments() {
            return Stream.of(
                Arguments.of(null, "user123", "Password123!", "user@example.com", "20251234", "영은"),
                Arguments.of("송영은", "", "Password123!", "user@example.com", "20251234", "영은"),
                Arguments.of("송영은", "user123", "", "user@example.com", "20251234", "영은"),
                Arguments.of("송영은", "user123", "Password123!", "", "20251234", "영은"),
                Arguments.of("송영은", "user123", "Password123!", "user@example.com", "", "영은"),
                Arguments.of("송영은", "user123", "Password123!", "user@example.com", "20251234", "")
            );
        }
    }
}
