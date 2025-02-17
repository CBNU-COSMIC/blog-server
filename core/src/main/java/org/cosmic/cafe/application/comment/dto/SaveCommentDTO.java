package org.cosmic.cafe.application.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveCommentDTO {

    private String content;
    private UUID postId;
    private UUID parentId;

}
