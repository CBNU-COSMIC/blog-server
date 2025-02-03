package org.cosmic.cafe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.UUID;
import org.cosmic.cafe.context.ControllerTest;
import org.cosmic.cafe.domain.member.Role;
import org.cosmic.cafe.dto.LoginPayload;
import org.cosmic.cafe.dto.SignInRequest;
import org.cosmic.cafe.dto.SignUpRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

public class AuthControllerTest extends ControllerTest {

    @Nested
    class 회원가입 {

        @Test
        void 올바른_사용자_정보는_200을_반환한다() throws Exception {
            String userId = "userId";
            String nickname = "nickname";
            String password = "password";
            String email = "email@email.com";
            String username = "username";
            LocalDateTime birth = LocalDateTime.now();
            String phone = "010-1234-5678";

            SignUpRequest signUpRequest = new SignUpRequest(
                    userId,
                    nickname,
                    password,
                    email,
                    username,
                    birth,
                    phone
            );

            mockMvc.perform(post("/api/auth/sign-up")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(signUpRequest)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class 로그인 {

        @Test
        void 올바른_사용자_정보는_200을_반환한다() throws Exception {
            String userId = "userId";
            String password = "password";
            LoginPayload loginPayload = new LoginPayload(UUID.randomUUID(), Role.GUEST);
            given(authService.signIn(userId, password)).willReturn(loginPayload);

            MockHttpSession session = new MockHttpSession();

            mockMvc.perform(post("/api/auth/sign-in")
                            .session(session)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(new SignInRequest(userId, password))))
                    .andExpect(status().isOk());

            assertThat(session.getAttribute("loginPayload")).isEqualTo(loginPayload);
        }
    }

    @Nested
    class 로그아웃 {

        @Test
        void 로그아웃_요청은_200을_반환한다() throws Exception {
            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(UUID.randomUUID(), Role.GUEST));

            mockMvc.perform(post("/api/auth/sign-out")
                            .session(session))
                    .andExpect(status().isOk());

            assertThat(session.isInvalid()).isTrue();
        }
    }
}
