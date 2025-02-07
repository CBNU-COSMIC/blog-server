package org.cosmic.cafe.domain.post;

import org.assertj.core.api.Assertions;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.stream.Stream;

public class PostTest {

    @Nested
    class Post는{

        @Test
        void 올바른_인자가_들어오면_객체가_생성된다(){

            //given
            UUID postId = UUID.randomUUID();
            UUID boardId = UUID.randomUUID();
            String title = "TEST TITLE";
            String content = "TEST CONTENT";
            Long hits = 1L;

            //when
            Post post = Post.builder()
                    .id(postId)
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build();

            //then
            Assertions.assertThat(post.getId()).isEqualTo(postId);
            Assertions.assertThat(post.getBoardId()).isEqualTo(boardId);
            Assertions.assertThat(post.getTitle()).isEqualTo(title);
            Assertions.assertThat(post.getContent()).isEqualTo(content);
            Assertions.assertThat(post.getHits()).isEqualTo(hits);
        }

        @ParameterizedTest
        @MethodSource("generateInvalidArguments")
        void 게시글이_게시판아이디_제목_본문을_모두_포함하지_않으면_예외를_발생시킨다(UUID boardId, String title, String content){

            //given
            UUID postId = UUID.randomUUID();

            //expect
            org.junit.jupiter.api.Assertions.assertThrows(BadRequestException.class,()-> Post.builder()
                    .id(postId)
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .build());
        }

        private static Stream<Arguments> generateInvalidArguments(){
            return Stream.of(
                    Arguments.of(null,"title","content"),
                    Arguments.of(UUID.randomUUID(),"","content"),
                    Arguments.of(UUID.randomUUID(),"title","")
            );
        }
    }
}
