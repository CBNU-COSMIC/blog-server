package org.cosmic.cafe;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.cosmic.cafe.application.comment.dto.ModifyCommentDTO;
import org.cosmic.cafe.application.comment.dto.SaveCommentDTO;
import org.cosmic.cafe.context.ControllerTest;
import org.cosmic.cafe.dto.LoginPayload;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.cosmic.cafe.domain.member.Role;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.UUID;

public class CommentControllerTest extends ControllerTest {

    @Nested
    class 댓글_작성{

        @Test
        void 올바른_댓글은_200을_반환한다() throws Exception {
            SaveCommentDTO saveCommentDTO = new SaveCommentDTO();
            saveCommentDTO.setPostId(UUID.randomUUID());
            saveCommentDTO.setContent("content");
            saveCommentDTO.setParentId(UUID.randomUUID());
            UUID memberId = UUID.randomUUID();

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            mockMvc.perform(post("/api/comment")
                    .session(session).contentType("application/json")
                    .content(objectMapper.writeValueAsString(saveCommentDTO)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class 댓글_수정{

        @Test
        void 올바른_댓글수정은_200을_반환한다() throws Exception {
            UUID commentId = UUID.randomUUID();
            ModifyCommentDTO modifyCommentDTO = new ModifyCommentDTO();
            modifyCommentDTO.setContent("modify content");
            UUID memberId = UUID.randomUUID();

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            mockMvc.perform(put("/api/comment/"+commentId)
                            .session(session).contentType("application/json")
                            .content(objectMapper.writeValueAsString(modifyCommentDTO)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class 댓글_삭제{

        @Test
        void 올바른_댓글삭제는_200을_반환한다() throws Exception {
            UUID commentId = UUID.randomUUID();
            UUID memberId = UUID.randomUUID();

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            mockMvc.perform(delete("/api/comment/"+commentId)
                            .session(session))
                    .andExpect(status().isOk());
        }
    }

}
