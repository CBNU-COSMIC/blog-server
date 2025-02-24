package org.cosmic.cafe.infra.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.cosmic.cafe.context.RepositoryContext;
import org.cosmic.cafe.domain.member.Member;
import org.cosmic.cafe.domain.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepositoryTest extends RepositoryContext {

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    class save_메서드는 {
        @Test
        void 멤버를_저장한다() {
            // given
            String memberId = "user123";
            String password = "User123!";
            String name = "영은";
            String email = "user@example.com";
            String studentNumber = "20251234";
            String nickname = "영은";

            // when
            Member savedMember = memberRepository.save(Member.builder()
                .name(name)
                .memberId(memberId)
                .password(password)
                .nickname(nickname)
                .studentNumber(studentNumber)
                .email(email)
                .build());

            // then
            Assertions.assertAll(
                () -> assertThat(savedMember.getName()).isEqualTo(name),
                () -> assertThat(savedMember.getId()).isNotNull(),
                () -> assertThat(savedMember.getMemberId()).isEqualTo(memberId),
                () -> assertThat(savedMember.getPassword()).isEqualTo(password),
                () -> assertThat(savedMember.getNickname()).isEqualTo(nickname),
                () -> assertThat(savedMember.getStudentNumber()).isEqualTo(studentNumber),
                () -> assertThat(savedMember.getEmail()).isEqualTo(email)
            );
        }
    }

    @Nested
    class findById_메서드는 {
        @Test
        void 해당_아이디가_존재하면_멤버_객체를_반환한다() {
            // given
            Member savedMember = memberRepository.save(Member.builder()
                .name("송영은")
                .memberId("user123")
                .password("User123!")
                .nickname("영은")
                .studentNumber("20251234")
                .email("user@example.com")
                .build());

            // when
            Optional<Member> foundMember = memberRepository.findById(savedMember.getId());

            // then
            assertThat(foundMember).isPresent();
            assertThat(foundMember.get().getId()).isEqualTo(savedMember.getId());
        }
    }

    @Nested
    class findByMemberId_메서드는 {
        @Test
        void 해당_멤버ID가_존재하면_멤버_객체를_반환한다() {
            // given
            String memberId = "user123";
            memberRepository.save(Member.builder()
                .name("송영은")
                .memberId(memberId)
                .password("User123!")
                .nickname("영은")
                .studentNumber("20251234")
                .email("user@example.com")
                .build());

            // when
            Optional<Member> foundMember = memberRepository.findByMemberId(memberId);

            // then
            assertThat(foundMember).isPresent();
            assertThat(foundMember.get().getMemberId()).isEqualTo(memberId);
        }
    }

    @Nested
    class findAll_메서드는 {
        @Test
        void 모든_멤버_객체를_반환한다() {
            // given
            memberRepository.save(Member.builder().name("소희").memberId("sohui").password("Sohui123!").nickname("소희").studentNumber("20250001").email("sohui@example.com").build());
            memberRepository.save(Member.builder().name("선웅").memberId("sunwoong").password("Sunwoong123!").nickname("선웅").studentNumber("20250002").email("sunwoong@example.com").build());

            // when
            List<Member> members = memberRepository.findAll();

            // then
            assertThat(members).hasSizeGreaterThanOrEqualTo(2);
        }
    }

    @Nested
    class deleteById_메서드는 {
        @Test
        void 해당_멤버를_삭제한다() {
            // given
            Member savedMember = memberRepository.save(Member.builder()
                .name("deleteName")
                .memberId("deletemember")
                .password("Delete123!")
                .nickname("delteNick")
                .studentNumber("20259999")
                .email("delete@example.com")
                .build());

            // when
            memberRepository.deleteById(savedMember.getId());

            // then
            Optional<Member> deletedMember = memberRepository.findById(savedMember.getId());
            assertThat(deletedMember).isEmpty();
        }
    }
}