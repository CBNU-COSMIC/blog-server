package org.cosmic.cafe.application.post.dto;

public record PostCreationPayload(
       String title,
       String content,
       String boardId
) {
}