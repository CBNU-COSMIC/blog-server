package org.cosmic.cafe;

import org.cosmic.cafe.context.ControllerTest;
import org.cosmic.cafe.dto.LoginPayload;
import org.cosmic.cafe.domain.member.Role;
import org.cosmic.cafe.dto.PostUpdateRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PostControllerTest extends ControllerTest {

    @Nested
    class 게시글_수정 {

        @Test
        void 정상적인_수정_요청은_200을_반환한다() throws Exception {
            // given
            UUID postId = UUID.randomUUID();
            UUID memberId = UUID.randomUUID();
            String title = "updatedTitle";
            String content = "updatedContent";

            PostUpdateRequest postUpdateRequest = new PostUpdateRequest(title, content);

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            // when & then
            mockMvc.perform(put("/api/posts/" + postId)
                            .session(session)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(postUpdateRequest)))
                    .andExpect(status().isOk());
        }
    }
}
