package org.cosmic.cafe;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;
import org.cosmic.cafe.application.member.dto.MemberCreationPayload;
import org.cosmic.cafe.application.member.dto.MemberDetailResponse;
import org.cosmic.cafe.application.member.dto.MemberUpdateRequest;
import org.cosmic.cafe.context.ControllerTest;
import org.cosmic.cafe.domain.member.Member;
import org.cosmic.cafe.domain.member.Role;
import org.cosmic.cafe.dto.LoginPayload;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

public class MemberControllerTest extends ControllerTest {

    @Nested
    class 회원_생성 {

        @Test
        void 정상적인_회원_생성_요청은_200을_반환한다() throws Exception {
            // given
            UUID memberId = UUID.randomUUID();
            MemberCreationPayload memberCreationPayload = new MemberCreationPayload(
                    "testName", "test12", "testNickname", "testPassword1!", "GUEST",
                    null, "1234567890", "12345678", null, "testEmail@test.com");

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            // when & then
            mockMvc.perform(post("/api/members")
                            .contentType("application/json")
                            .session(session)
                            .content(objectMapper.writeValueAsString(memberCreationPayload)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class 회원_조회 {

        @Test
        void 회원_상세_조회_요청은_200을_반환한다() throws Exception {
            // given
            UUID memberId = UUID.randomUUID();
            String nickname = "testname";
            Member member = new Member(
                    UUID.randomUUID(), "testName", "testid", "testname",
                    "testPassword1!", "USER", "avatarUrl", "1234567890",
                    "12345678", null, "testemail@test.com");
            MemberDetailResponse expectedResponse = MemberDetailResponse.of(member);

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            given(memberService.getMemberDetail(nickname)).willReturn(expectedResponse);

            // when & then
            mockMvc.perform(get("/api/members/{nickname}", nickname)
                            .session(session)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nickname").value("testname"))
                    .andExpect(jsonPath("$.name").value("testName"));
        }

        @Test
        void 회원_목록_조회_요청은_200을_반환한다() throws Exception {
            // given
            List<Member> members = List.of(
                    new Member(UUID.randomUUID(), "duddms", "testid1", "nickname1", "Password11!", "MEMBER",
                            "avatarUrl", "1234567890", "1234567", null, "email1@test.com"),
                    new Member(UUID.randomUUID(), "thd", "testid2", "nickname2", "Password22!", "GUEST", "avatarUrl",
                            "7890123456", "7654321", null, "email2@test.com")
            );

            given(memberService.findAll()).willReturn(members);

            // when & then
            mockMvc.perform(get("/api/members")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].name").value("testName1"))
                    .andExpect(jsonPath("$[1].nickname").value("nickname2"));
        }
    }

    @Nested
    class 회원_수정 {

        @Test
        void 정상적인_회원_수정_요청은_200을_반환한다() throws Exception {
            // given
            UUID memberId = UUID.randomUUID();
            MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("updatedName", "updatedNickname",
                    "111111111", null, "updatedEmail");

            // when & then
            mockMvc.perform(put("/api/members/{id}", memberId)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(memberUpdateRequest)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class 회원_삭제 {

        @Test
        void 정상적인_회원_삭제_요청은_200을_반환한다() throws Exception {
            // given
            UUID memberId = UUID.randomUUID();

            // when & then
            mockMvc.perform(delete("/api/members/{id}", memberId)
                            .contentType("application/json"))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class 회원_역할_수정 {

        @Test
        void 정상적인_회원_역할_수정_요청은_200을_반환한다() throws Exception {
            // given
            String nickname = "testNickname";
            String role = "ADMIN";

            // when & then
            mockMvc.perform(put("/api/members/{nickname}/role", nickname)
                            .param("role", role)
                            .contentType("application/json"))
                    .andExpect(status().isOk());
        }
    }
}
