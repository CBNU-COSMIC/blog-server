package org.cosmic.cafe.domain.Comment;

import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.Comment.exception.CommentErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Comment {
    private UUID id;
    private UUID userId;
    private String content;
    private UUID postId;
    private LocalDateTime createdAt;

    private static String ERROR_CONTENT_IS_NULL = "댓글은 비어있을 수 없습니다.";

    @Builder
    public Comment(UUID id, UUID userId, String content, UUID postId, LocalDateTime createdAt) {
        validateContent(content);
        
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new BadRequestException(ERROR_CONTENT_IS_NULL, CommentErrorCode.CONTENT_IS_NULL);
        }
    }
}