//package org.cosmic.cafe;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//import java.util.UUID;
//import org.cosmic.cafe.application.member.dto.MemberCreationPayload;
//import org.cosmic.cafe.application.member.dto.MemberDetailResponse;
//import org.cosmic.cafe.application.member.dto.MemberListResponse;
//import org.cosmic.cafe.application.member.dto.MemberUpdateRequest;
//import org.cosmic.cafe.context.ControllerTest;
//import org.cosmic.cafe.domain.member.Member;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//public class MemberControllerTest extends ControllerTest {
//
//    @Nested
//    class 회원_생성 {
//
//        @Test
//        void 정상적인_회원_생성_요청은_200을_반환한다() throws Exception {
//            // given
//            MemberCreationPayload memberCreationPayload = new MemberCreationPayload(
//                "testName", "testId", "testNickname", "testPassword1!", "GUEST",
//                null, "1234567890", "12345678", null, "testEmail@test.com");
//
//            // when & then
//            mockMvc.perform(post("/api/members")
//                    .contentType("application/json")
//                    .content(objectMapper.writeValueAsString(memberCreationPayload)))
//                .andExpect(status().isOk());
//        }
//    }
//
//    @Nested
//    class 회원_조회 {
//
//        @Test
//        void 회원_상세_조회_요청은_200을_반환한다() throws Exception {
//            // given
//            String memberId = "testId";
//            Member member = new Member(
//                UUID.randomUUID(), "testName", "testId", "testNickname",
//                "testPassword", "USER", "avatarUrl", "1234567890",
//                "12345", null, "testEmail@test.com");
//            MemberDetailResponse expectedResponse = MemberDetailResponse.of(member);
//
//            given(memberService.getMemberDetail(memberId)).willReturn(expectedResponse);
//
//            // when & then
//            mockMvc.perform(get("/api/members/{memberId}", memberId)
//                    .contentType("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.memberId").value("testId"))
//                .andExpect(jsonPath("$.name").value("testName"));
//        }
//
//        @Test
//        void 회원_목록_조회_요청은_200을_반환한다() throws Exception {
//            // given
//            List<MemberListResponse> expectedResponse = List.of(
//                MemberListResponse.builder().id(UUID.randomUUID()).name("testName1").nickname("nickname1").email("email1").phoneNumber("123456").build(),
//                MemberListResponse.builder().id(UUID.randomUUID()).name("testName2").nickname("nickname2").email("email2").phoneNumber("789012").build()
//            );
//            given(memberService.findAll()).willReturn(expectedResponse);
//
//            // when & then
//            mockMvc.perform(get("/api/members")
//                    .contentType("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("testName1"))
//                .andExpect(jsonPath("$[1].nickname").value("nickname2"));
//        }
//    }
//
//    @Nested
//    class 회원_수정 {
//
//        @Test
//        void 정상적인_회원_수정_요청은_200을_반환한다() throws Exception {
//            // given
//            UUID memberId = UUID.randomUUID();
//            MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("updatedName", "updatedNickname", "111111111", null, "updatedEmail");
//
//            // when & then
//            mockMvc.perform(put("/api/members/{id}", memberId)
//                    .contentType("application/json")
//                    .content(objectMapper.writeValueAsString(memberUpdateRequest)))
//                .andExpect(status().isOk());
//        }
//    }
//
//    @Nested
//    class 회원_삭제 {
//
//        @Test
//        void 정상적인_회원_삭제_요청은_200을_반환한다() throws Exception {
//            // given
//            UUID memberId = UUID.randomUUID();
//
//            // when & then
//            mockMvc.perform(delete("/api/members/{id}", memberId)
//                    .contentType("application/json"))
//                .andExpect(status().isOk());
//        }
//    }
//
//    @Nested
//    class 회원_역할_수정 {
//
//        @Test
//        void 정상적인_회원_역할_수정_요청은_200을_반환한다() throws Exception {
//            // given
//            String nickname = "testNickname";
//            String role = "ADMIN";
//
//            // when & then
//            mockMvc.perform(put("/api/members/{nickname}/role", nickname)
//                    .param("role", role)
//                    .contentType("application/json"))
//                .andExpect(status().isOk());
//        }
//    }
//}
