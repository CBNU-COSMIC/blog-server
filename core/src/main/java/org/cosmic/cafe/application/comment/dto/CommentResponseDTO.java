package org.cosmic.cafe.application.comment.dto;

import lombok.Getter;
import lombok.Setter;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.member.Member;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment, Member member){
        if(member!=null)
            this.userName = member.getUserName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
