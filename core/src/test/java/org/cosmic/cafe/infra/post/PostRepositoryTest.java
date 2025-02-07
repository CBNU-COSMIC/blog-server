package org.cosmic.cafe.infra.post;

import org.cosmic.cafe.context.RepositoryContext;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.domain.post.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PostRepositoryTest extends RepositoryContext {

    @Autowired
    private PostRepository postRepository;

    @Nested
    class save_메서드는{

        @Test
        void 게시글을_저장한다(){

            //given
            String boardId = "게시판";
            String title = "TEST TITLE";
            String content = "TEST CONTENT";
            Long hits = 1L;

            //when
            Post savedPost = postRepository.save(Post.builder()
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            //then
            Assertions.assertAll(
                    () -> assertThat(savedPost.getId()).isNotNull(),
                    () -> assertThat(savedPost.getBoardId()).isEqualTo(boardId),
                    () -> assertThat(savedPost.getTitle()).isEqualTo(title),
                    () -> assertThat(savedPost.getContent()).isEqualTo(content),
                    () -> assertThat(savedPost.getHits()).isEqualTo(hits)
                    );
        }
    }

    @Nested
    class findById_메서드는{

        @Test
        void 해당_아이디가_존재하면_게시글_객체를_반환한다(){

            //given
            String boardId = "게시판";
            String title = "TEST TITLE";
            String content = "TEST CONTENT";
            Long hits = 1L;

            Post savedPost = postRepository.save(Post.builder()
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            //when
            Post foundPost = postRepository.findById(savedPost.getId()).orElse(null);

            //then
            Assertions.assertAll(
                    ()-> assertThat(foundPost).isNotNull(),
                    ()-> assertThat(foundPost.getId()).isEqualTo(savedPost.getId())
            );
        }
    }

}
