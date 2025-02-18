package org.cosmic.cafe;

import org.cosmic.cafe.application.post.dto.PostDetailResponse;
import org.cosmic.cafe.application.post.dto.PostListResponse;
import org.cosmic.cafe.context.ControllerTest;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.context.ControllerTest;
import org.cosmic.cafe.dto.LoginPayload;
import org.cosmic.cafe.domain.member.Role;
import org.cosmic.cafe.dto.PostUpdateRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PostControllerTest extends ControllerTest {

    @Nested
    class 게시글_조회 {

        @Test
        void 게시글_상세_조회_요청은_200을_반환한다() throws Exception {
            // given
            UUID postId = UUID.randomUUID();
            UUID memberId = UUID.randomUUID();

            Post post = new Post(postId, memberId , "게시판", "Title1", "Content1", LocalDateTime.now(), 1L);
            PostDetailResponse expectedResponse = PostDetailResponse.of(post, "author1");

            given(postService.getPostDetail(postId)).willReturn(expectedResponse);

            MockHttpSession session = new MockHttpSession();
            session.setAttribute("loginPayload", new LoginPayload(memberId, Role.GUEST));

            // when & then
            mockMvc.perform(get("/api/posts/" + postId)
                            .session(session)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("Title1"))
                    .andExpect(jsonPath("$.content").value("Content1"));
        }

        @Test
        void 게시글_목록_조회_요청은_200을_반환한다() throws Exception {
            // given
            String boardId = "게시판";
            int page = 1;

            List<PostListResponse> expectedResponse = List.of(
                    PostListResponse.builder()
                            .postId(UUID.randomUUID())
                            .title("Title1")
                            .author("author1")
                            .date(LocalDateTime.now())
                            .hits(1L)
                            .build(),
                    PostListResponse.builder()
                            .postId(UUID.randomUUID())
                            .title("Title2")
                            .author("author2")
                            .date(LocalDateTime.now().minusHours(1))
                            .hits(1L)
                            .build()
            );

            given(postService.getPostsByBoardId(boardId, page)).willReturn(expectedResponse);

            // when & then
            mockMvc.perform(get("/api/posts")
                            .param("boardId", boardId)
                            .param("page", String.valueOf(page))
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].title").value("Title1"))
                    .andExpect(jsonPath("$[1].title").value("Title2"))
                    .andExpect(jsonPath("$[0].author").value("author1"))
                    .andExpect(jsonPath("$[1].author").value("author2"));
        }
    }

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
