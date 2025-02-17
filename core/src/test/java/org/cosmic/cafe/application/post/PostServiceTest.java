package org.cosmic.cafe.application.post;

import org.cosmic.cafe.application.post.dto.PostCreationPayload;
import org.cosmic.cafe.application.post.dto.PostListResponse;
import org.cosmic.cafe.context.ServiceContext;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.exception.type.NotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceIntegrationTest extends ServiceContext {

    @Nested
    class 게시글_생성_테스트 {

        @Test
        void 게시글을_정상적으로_생성할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            PostCreationPayload postCreationPayload = new PostCreationPayload(
                    "Title",
                    "Content",
                    "게시판"
            );

            // when
            postService.createPost(memberId, postCreationPayload);

            // then
            List<Post> posts = postRepository.findAll();
            assertThat(posts.size()).isEqualTo(1);
            assertThat(posts.get(0).getTitle()).isEqualTo("Title");
        }
    }

    @Nested
    class 게시글_조회_테스트 {

        @Test
        void 특정_게시글을_조회할_수_있다() {
            //given
            UUID memberID = UUID.randomUUID();
            Post post = postRepository.save(new Post(
                    null, memberID, "게시판", "Title1", "Content1", LocalDateTime.now(),1L
            ));

            // when
            Post foundPost = postService.getPost(post.getId());

            // then
            assertThat(foundPost.getId()).isEqualTo(post.getId());
            assertThat(foundPost.getTitle()).isEqualTo(post.getTitle());
        }

        @Test
        void 존재하지_않는_게시글을_조회하면_예외가_발생한다() {
            // expect
            assertThatThrownBy(() -> postService.getPost(UUID.randomUUID()))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessageContaining("게시글이 존재하지 않습니다.");
        }

        @Test
        void 게시판의_글_목록을_10개씩_불러와_조회할_수_있다() {
            // given
            String boardId = "게시판";
            UUID memberId = UUID.randomUUID();

            for(int i = 1; i <= 11; i++) {
                postRepository.save(new Post(
                        null,
                        memberId,
                        boardId,
                        "Title" + i,
                        "Content" + i,
                        LocalDateTime.now().minusHours(i),
                        1L

                ));
            }

            // when
            List<PostListResponse> firstPage = postService.getPostsByBoardId(boardId, 1);
            List<PostListResponse> secondPage = postService.getPostsByBoardId(boardId, 2);

            // then
            assertAll(
                    () -> assertThat(firstPage.size()).isEqualTo(10),
                    () -> assertThat(secondPage.size()).isEqualTo(1),
                    () -> assertThat(firstPage.get(0).getTitle()).isEqualTo("Title1"),
                    () -> assertThat(firstPage.get(9).getTitle()).isEqualTo("Title10"),
                    () -> assertThat(secondPage.get(0).getTitle()).isEqualTo("Title11")
            );
        }

        @Test
        void 게시글이_없는_페이지를_조회하면_빈_리스트가_반환된다() {
            // given
            String boardId = "게시판";
            int nonExistentPage = 999;

            // when
            List<PostListResponse> posts = postService.getPostsByBoardId(boardId, nonExistentPage);

            // then
            assertThat(posts.size()).isEqualTo(0);
        }
    }

    @Nested
    class 게시글_수정_테스트 {

        @Test
        void 본인이_게시글을_수정할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            Post post = postRepository.save(new Post(
                    null, memberId, "게시판", "Title1", "Content1",LocalDateTime.now(),1L
            ));

            // when
            postService.update("Title2","Content2", memberId, post.getId());

            // then
            Post updatedpost = postRepository.findById(post.getId()).get();
            assertAll(
                    () -> assertThat(updatedpost.getTitle()).isEqualTo("Title2"),
                    () -> assertThat(updatedpost.getContent()).isEqualTo("Content2"),
                    () -> assertThat(updatedpost.getMemberId()).isEqualTo(memberId),
                    () -> assertThat(updatedpost.getBoardId()).isEqualTo("게시판"),
                    () -> assertThat(updatedpost.getHits()).isEqualTo(1L)
            );
        }

        @Test
        void 다른_사용자의_게시글은_수정할_수_없다() {
            // given
            UUID memberId = UUID.randomUUID();
            UUID otherMemberId = UUID.randomUUID();
            Post post = postRepository.save(new Post(
                    null, memberId, "게시판", "Title1", "Content1",LocalDateTime.now(),1L
            ));

            // expected
            assertThrows(BadRequestException.class, () ->
                    postService.update("Title2","Content2", otherMemberId, post.getId())
            );
        }

        @Test
        void 존재하지_않는_게시글은_수정할_수_없다() {
            // given
            UUID memberId = UUID.randomUUID();
            UUID nonExistentPostId = UUID.randomUUID();

            // expected
            assertThrows(NotFoundException.class, () ->
                    postService.update("Title1", "Content1", memberId, nonExistentPostId));
        }
    }

    @Nested
    class 게시글_삭제_테스트 {
        @Test
        void 본인이_게시글을_삭제할_수_있다() {
            //given
            UUID memberId = UUID.randomUUID();
            Post post = postRepository.save(new Post(
                    null, memberId, "게시판", "Title1", "Content1",LocalDateTime.now(),1L
            ));

            // when
            postService.deletePost(memberId, post.getId());

            // then
            assertThat(postRepository.findById(post.getId())).isEmpty();
        }

        @Test
        void 다른_사용자의_게시글을_삭제_할_수_없다() {
            //given
            UUID memberId = UUID.randomUUID();
            UUID otherMemberId = UUID.randomUUID();
            Post post = postRepository.save(new Post(
                    null, memberId, "게시판", "Title1", "Content1",LocalDateTime.now(),1L
            ));

            // expected
            assertThrows(BadRequestException.class, () ->
                    postService.deletePost(otherMemberId, post.getId()));
        }

        @Test
        void 존재하지_않는_게시글은_삭제할_수_없다() {
            // given
            UUID memberId = UUID.randomUUID();
            UUID nonExistentPostId = UUID.randomUUID();

            // expected
            assertThrows(NotFoundException.class, () ->
                    postService.deletePost(memberId, nonExistentPostId));
        }
    }
}
