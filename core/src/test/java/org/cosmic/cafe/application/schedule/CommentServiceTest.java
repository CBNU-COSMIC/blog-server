package org.cosmic.cafe.application.schedule;


import org.cosmic.cafe.context.ServiceContext;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentServiceTest extends ServiceContext {

    @Nested
    class 댓글_저장_테스트 {

        @Test
        void 댓글을_정상적으로_저장할_수_있다() {
            //given
            UUID memberId = UUID.randomUUID();
            UUID parentId = UUID.randomUUID();
            UUID postID = UUID.randomUUID();

            //when
            UUID commentId = commentService.saveComment(memberId, "content", postID, parentId);

            //then
            assertThat(commentRepository.findById(commentId).isPresent()).isTrue();
        }
    }

    @Nested
    class 댓글_조회_테스트 {

        @Test
        void 게시글_아이디로_댓글을_조회할_수_있다() {
            //given
            UUID postID = UUID.randomUUID();
            Comment comment = Comment.builder()
                    .content("content1").postId(postID).build();

            commentRepository.save(comment);

            //when
            List<Comment> comments = commentRepository.findByPostId(postID);

            //then
            assertThat(comments).extracting(Comment::getContent).containsExactlyInAnyOrder("content1");
        }

    }

    @Nested
    class 댓글_삭제_테스트 {

        @Test
        void 본인_댓글을_삭제할_수_있다(){
            //given
            UUID memberId = UUID.randomUUID();
            Comment savedComment = commentRepository.save(Comment.builder().userId(memberId).content("content").build());
            String commentId = savedComment.getId().toString();

            //when
            commentService.deleteComment(commentId,memberId);

            //then
            Comment foundComment = commentRepository.findById(savedComment.getId()).orElse(null);
            assertThat(foundComment).isNull();
        }

        @Test
        void 본인이_아닐_경우_댓글_삭제가_불가능하다(){

            //given
            UUID memberId = UUID.randomUUID();
            Comment savedComment = commentRepository.save(Comment.builder().userId(memberId).content("content").build());
            String commentId = savedComment.getId().toString();

            //expect
            Assertions.assertThrows(BadRequestException.class,()->commentService.deleteComment(commentId,UUID.randomUUID()));
        }

        @Test
        void 삭제하려는_댓글의_ID가_존재하지_않을경우_예외를_터뜨린다(){
            //given
            UUID memberId = UUID.randomUUID();
            Comment savedComment = commentRepository.save(Comment.builder().userId(memberId).content("content").build());
            String commentId = savedComment.getId().toString();

            //expect
            Assertions.assertThrows(BadRequestException.class, ()->commentService.deleteComment(UUID.randomUUID().toString(),memberId));
        }
    }
}
