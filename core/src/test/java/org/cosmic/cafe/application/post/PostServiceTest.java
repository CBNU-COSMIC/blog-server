package org.cosmic.cafe.application.post;

import org.cosmic.cafe.context.ServiceContext;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.exception.type.NotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceIntegrationTest extends ServiceContext {

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
}
