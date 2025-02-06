package org.cosmic.cafe.infra.comment;

import org.cosmic.cafe.context.RepositoryContext;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CommentRepositoryTest extends RepositoryContext {

    @Autowired
    private CommentRepository commentRepository;

    @Nested
    class save_메서드는 {

        @Test
        void 댓글을_저장한다() {
            // given
            UUID userId = UUID.randomUUID();
            String content = "test content";
            UUID postId = UUID.randomUUID();
            UUID parentId = UUID.randomUUID();
            LocalDateTime createdAt = LocalDateTime.now();

            // when
            Comment savedComment = commentRepository.save(Comment.builder()
                    .userId(userId)
                    .content(content)
                    .postId(postId)
                    .parentId(parentId)
                    .createdAt(createdAt)
                    .build());

            // then
            assertAll(
                    () -> assertThat(savedComment.getId()).isNotNull(),
                    () -> assertThat(savedComment.getUserId()).isEqualTo(userId),
                    () -> assertThat(savedComment.getContent()).isEqualTo(content),
                    () -> assertThat(savedComment.getPostId()).isEqualTo(postId),
                    () -> assertThat(savedComment.getCreatedAt()).isEqualTo(createdAt)
            );
        }

    }

    @Nested
    class findById_메서드는 {

        @Test
        void 해당_아이디가_존재하면_댓글을_반환한다() {
           // given
            UUID userId = UUID.randomUUID();
            String content = "test content";
            UUID postId = UUID.randomUUID();
            UUID parentId = UUID.randomUUID();
            LocalDateTime createdAt = LocalDateTime.now();

            Comment savedComment = commentRepository.save(Comment.builder()
                    .userId(userId)
                    .content(content)
                    .postId(postId)
                    .parentId(parentId)
                    .createdAt(createdAt)
                    .build());

            // when
            Comment foundComment = commentRepository.findById(savedComment.getId()).orElse(null);

            // then
            assertAll(
                    () -> assertThat(foundComment).isNotNull(),
                    () -> assertThat(foundComment.getId()).isEqualTo(savedComment.getId())
            );

        }

        @Test
        void 해당_아이디가_존재하지_않으면_null을_반환한다() {
            // given
            UUID id = UUID.randomUUID();

            // when
            Comment foundComment = commentRepository.findById(id).orElse(null);

            // then
            assertThat(foundComment).isNull();
        }
    }

    @Nested
    class findByPostId_메서드는 {

        @Test
        void 해당_게시글의_모든_댓글을_조회한다() {
            // given
            UUID userId = UUID.randomUUID();
            String content = "test content";
            UUID postId = UUID.randomUUID();
            UUID parentId = UUID.randomUUID();
            LocalDateTime createdAt = LocalDateTime.now();

            Comment savedComment1 = commentRepository.save(Comment.builder()
                    .userId(userId)
                    .content(content)
                    .postId(postId)
                    .parentId(parentId)
                    .createdAt(createdAt)
                    .build());

            Comment savedComment2 = commentRepository.save(Comment.builder()
                    .userId(userId)
                    .content(content)
                    .postId(postId)
                    .parentId(parentId)
                    .createdAt(createdAt)
                    .build());

            // when
            List<Comment> comments = commentRepository.findByPostId(postId);

            // then
            assertThat(comments.size()).isEqualTo(2);
            assertThat(comments.get(0).getPostId()).isEqualTo(postId);
        }
    }

    @Nested
    class findByParentId_메서드는 {

        @Test
        void 부모_댓글의_모든_대댓글을_조회한다() {
            // given
            UUID userId = UUID.randomUUID();
            String content = "test content";
            UUID postId = UUID.randomUUID();
            UUID parentId = UUID.randomUUID();
            LocalDateTime createdAt = LocalDateTime.now();

            Comment savedComment1 = commentRepository.save(Comment.builder()
                    .userId(userId)
                    .content(content)
                    .postId(postId)
                    .parentId(parentId)
                    .createdAt(createdAt)
                    .build());

            Comment savedComment2 = commentRepository.save(Comment.builder()
                    .userId(userId)
                    .content(content)
                    .postId(postId)
                    .parentId(parentId)
                    .createdAt(createdAt)
                    .build());

            // when
            List<Comment> comments = commentRepository.findByParentId(parentId);

            // then
            assertThat(comments.size()).isEqualTo(2);
            assertThat(comments.get(0).getParentId()).isEqualTo(parentId);
        }
    }
}