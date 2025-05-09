package org.cosmic.cafe.application.member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import org.cosmic.cafe.application.member.dto.MemberCreationPayload;
import org.cosmic.cafe.application.member.dto.MemberUpdateRequest;
import org.cosmic.cafe.context.ServiceContext;
import org.cosmic.cafe.domain.member.Member;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class MemberServiceTest extends ServiceContext {

    @Nested
    class 회원_생성_테스트 {

        @Test
        void 회원을_정상적으로_생성할_수_있다() {
            //given
            MemberCreationPayload payload = new MemberCreationPayload(
                "송영은",
                "member123",
                "영은",
                "P@ssword123!",
                "Member",
                null,
                "010-1234-5678",
                "20251234",
                LocalDateTime.of(2000,1,1,0,0),
                "young@test.com"
            );

            //when
            memberService.createMember(payload);

            //then
            Optional<Member> foundMember = memberRepository.findByMemberId("member123");
            assertThat(foundMember).isPresent();
            assertThat(foundMember.get().getNickname()).isEqualTo("영은");
        }
    }

    @Nested
    class 회원_수정_테스트 {

        @Test
        void 회원_정보를_정상적으로_수정할_수_있다() {
            // Given
            Member member = new Member(
                null,
                "이름",
                "member123",
                "닉네임",
                "Password111!",
                "Member",
                null,
                "010-1234-0000",
                "20250000",
                LocalDateTime.of(2000,1,1,0,0),
                "email@example.com"
            );
            Member savedMember = memberRepository.save(member);

            // When
            MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest();
            memberUpdateRequest.setName("안녕하세요");
            memberUpdateRequest.setNickname("안녕");
            memberUpdateRequest.setPhoneNumber("010-3333-4444");
            memberUpdateRequest.setBirth(LocalDateTime.of(1000, 1, 1, 0, 0));
            memberUpdateRequest.setEmail("newhello@test.com");

            memberService.updateMember(savedMember.getId(), memberUpdateRequest);

            // Then
            Member updatedMember = memberRepository.findById(savedMember.getId()).orElseThrow();
            assertThat(updatedMember.getName()).isEqualTo("안녕하세요");
            assertThat(updatedMember.getNickname()).isEqualTo("안녕");
            assertThat(updatedMember.getPhoneNumber()).isEqualTo("010-3333-4444");
            assertThat(updatedMember.getBirth()).isEqualTo(LocalDateTime.of(1000, 1, 1,0,0));  // LocalDate 확인
            assertThat(updatedMember.getEmail()).isEqualTo("newhello@test.com");
        }
    }
    @Nested
    class 회원_삭제_테스트 {

        @Test
        void 회원을_정상적으로_삭제할_수_있다() {
            // given
            Member member = new Member(
                null, "name", "member11", "nickname",
                "Password123!", "GUEST", null, "010-1234-5678",
                "20241234", LocalDateTime.now(), "email@example.com"
            );
            Member savedMember = memberRepository.save(member);

            // when
            memberService.withdrawMember(savedMember.getId());

            // then
            Optional<Member> deletedMember = memberRepository.findById(savedMember.getId());
            assertThat(deletedMember).isEmpty();
        }
    }

    @Nested
    class 회원_조회_테스트 {

        @Test
        void 닉네임으로_회원_조회_가능하다() {
            // given
            Member member = new Member(
                null, "name", "member123", "nickname",
                "Password123!", "GUEST", null, "010-1234-5678",
                "20241234", LocalDateTime.now(), "email@example.com"
            );
            memberRepository.save(member);

            // when
            Optional<Member> foundMember = memberService.findByNickname("nickname");

            // then
            assertThat(foundMember).isPresent();
            assertThat(foundMember.get().getMemberId()).isEqualTo("member123");
        }
    }

    @Nested
    class 회원_역할_업데이트_테스트 {

        @Test
        void 역할을_정상적으로_업데이트할_수_있다() {
            // given
            Member member = new Member(
                null,
                "이름",
                "member34",
                "닉네임",
                "Password111!",
                "Member",
                null,
                "010-1234-0000",
                "20250000",
                LocalDateTime.now(),
                "email@example.com"
            );
            memberRepository.save(member);

            // when
            memberService.updateMemberRole("닉네임", "President");

            // then
            Optional<Member> updatedMember = memberRepository.findByNickname("닉네임");
            assertThat(updatedMember).isPresent();
            assertThat(updatedMember.get().getRole()).isEqualTo("President");
        }
    }
}


