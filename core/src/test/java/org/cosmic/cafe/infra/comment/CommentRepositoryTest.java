package org.cosmic.cafe.infra.comment;

import org.cosmic.cafe.context.RepositoryContext;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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
}