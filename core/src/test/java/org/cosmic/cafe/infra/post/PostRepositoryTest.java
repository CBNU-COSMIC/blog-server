package org.cosmic.cafe.infra.post;

import org.cosmic.cafe.context.RepositoryContext;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.domain.post.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

    @Nested
    class findByTitle_메서드는{

        @Test
        void 해당_제목이_존재하면_게시글_객체를_반환한다(){

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
            List<Post> posts = postRepository.findByTitle(title);

            //then
            for (Post post : posts) {
                org.assertj.core.api.Assertions.assertThat(post.getTitle()).isEqualTo(title);
            }
        }
    }

    @Nested
    class findAll_메서드는{

        @Test
        void 모든_게시글_객체를_반환한다(){

            //given
            String boardId = "게시판";
            String title = "TEST TITLE";
            String content = "TEST CONTENT";
            Long hits = 1L;

            Post savedPost1 = postRepository.save(Post.builder()
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            boardId = "공지사항";
            title = "TEST TITLE2";
            content = "TEST CONTENT2";
            hits = 2L;

            Post savedPost2 = postRepository.save(Post.builder()
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            //when
            List<Post> posts = postRepository.findAll();

            //then
            org.assertj.core.api.Assertions.assertThat(posts)
                    .extracting(Post::getTitle)
                    .containsExactlyInAnyOrder(savedPost1.getTitle(), savedPost2.getTitle());
        }
    }

    @Nested
    class deleteById_메서드는{

        @Test
        void 해당_게시물을_삭제한다(){

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
            postRepository.deleteById(savedPost.getId());

            //then
            Post post = postRepository.findById(savedPost.getId()).orElse(null);
            org.assertj.core.api.Assertions.assertThat(post).isNull();
        }
    }

    @Nested
    class findByTitleContaining_메서드는{

        @Test
        void 문자열을_제목에_포함하고_있는_모든_게시글_객체를_반환한다(){

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

            boardId = "공지사항";
            String title2 = "TEST TITLE2";
            content = "TEST CONTENT2";
            hits = 2L;

            Post savedPost2 = postRepository.save(Post.builder()
                    .boardId(boardId)
                    .title(title2)
                    .content(content)
                    .hits(hits)
                    .build());

            //when
            List<Post> posts = postRepository.findByTitleContaining("TEST");


            //then
            org.assertj.core.api.Assertions.assertThat(posts)
                    .extracting(Post::getTitle)
                    .containsExactlyInAnyOrder(title,title2);
        }
    }

    @Nested
    class findByBoardId_메서드는{

        @Test
        void 게시글이_존재하는_게시판_아이디에_해당하는_게시글_객체들을_반환한다(){

            //given
            String boardId = "게시판";
            String title = "게시판 TITLE";
            String content = "TEST CONTENT";
            Long hits = 1L;

            Post savedPost = postRepository.save(Post.builder()
                    .boardId(boardId)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            String boardId2 = "공지사항";
            title = "공지사항 TITLE2";
            content = "TEST CONTENT2";
            hits = 2L;

            Post savedPost2 = postRepository.save(Post.builder()
                    .boardId(boardId2)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            boardId2 = "공지사항";
            title = "공지사항 TITLE3";
            content = "TEST CONTENT2";
            hits = 2L;

            Post savedPost3 = postRepository.save(Post.builder()
                    .boardId(boardId2)
                    .title(title)
                    .content(content)
                    .hits(hits)
                    .build());

            //when
            List<Post> foundPost1 = postRepository.findByBoardId(boardId);
            List<Post> foundPost2 = postRepository.findByBoardId(boardId2);

            //then
            org.assertj.core.api.Assertions.assertThat(foundPost1)
                    .extracting(Post::getTitle)
                    .containsExactlyInAnyOrder("게시판 TITLE");

            org.assertj.core.api.Assertions.assertThat(foundPost2)
                    .extracting(Post::getTitle)
                    .containsExactlyInAnyOrder("공지사항 TITLE2","공지사항 TITLE3");
        }
    }
}
