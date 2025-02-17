package org.cosmic.cafe.application.schedule;


import org.assertj.core.api.Assertions;
import org.cosmic.cafe.application.comment.dto.SaveCommentDTO;
import org.cosmic.cafe.context.ServiceContext;
import org.cosmic.cafe.domain.Comment.Comment;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class CommentServiceTest extends ServiceContext {

    @Nested
    class 댓글_저장_테스트{

        @Test
        void 댓글을_정상적으로_저장할_수_있다(){
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
    class 댓글_조회_테스트{

        @Test
        void 게시글_아이디로_댓글을_조회할_수_있다(){
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
}
