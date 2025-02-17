package org.cosmic.cafe.application.schedule;


import org.assertj.core.api.Assertions;
import org.cosmic.cafe.application.comment.dto.SaveCommentDTO;
import org.cosmic.cafe.context.ServiceContext;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
    
}
