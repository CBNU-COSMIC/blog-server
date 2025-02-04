package org.cosmic.cafe.domain.comment.domain;

import org.assertj.core.api.Assertions;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.infra.comment.entity.CommentEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {

    @Nested
    class Comment는 {

        @Test
        void 올바른_인자가_들어오면_객체가_생성된다() {
            // given
            UUID userId = UUID.randomUUID();
            UUID postId = UUID.randomUUID();
            String content = "test content";

            // when
            Comment comment = Comment.builder()
                    .userId(userId)
                    .postId(postId)
                    .content(content)
                    .createdAt(LocalDateTime.now())
                    .build();

            // then
            assertThat(comment.getUserId()).isEqualTo(userId);
            assertThat(comment.getPostId()).isEqualTo(postId);
            assertThat(comment.getContent()).isEqualTo(content);
        }

        @ParameterizedTest
        @MethodSource("provideInvalidContent")
        void content가_null이거나_비어있으면_예외가_발생한다(String content) {
            // given
            UUID userId = UUID.randomUUID();
            UUID postId = UUID.randomUUID();

            // expected
            assertThrows(BadRequestException.class, () -> Comment.builder()
                    .userId(userId)
                    .postId(postId)
                    .content(content)
                    .build());
        }

        private static Stream<Arguments> provideInvalidContent() {
            return Stream.of(
                    Arguments.of((String) null),
                    Arguments.of(""),
                    Arguments.of("   ")
            );
        }

        @Nested
        class of_메서드는 {

            @Test
            void CommentEntity를_받아_Comment로_변환한다() {
                // given
                UUID id = UUID.randomUUID();
                UUID userId = UUID.randomUUID();
                UUID postId = UUID.randomUUID();
                String content = "test content";

                CommentEntity entity = CommentEntity.builder()
                        .id(id)
                        .userId(userId)
                        .postId(postId)
                        .content(content)
                        .build();

                // when
                Comment comment = Comment.of(entity);

                // then
                Assertions.assertThat(comment.getId()).isEqualTo(id);
                Assertions.assertThat(comment.getUserId()).isEqualTo(userId);
                Assertions.assertThat(comment.getPostId()).isEqualTo(postId);
                Assertions.assertThat(comment.getContent()).isEqualTo(content);
            }
        }
    }
}
