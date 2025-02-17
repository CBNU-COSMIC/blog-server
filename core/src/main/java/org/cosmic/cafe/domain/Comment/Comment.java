package org.cosmic.cafe.domain.Comment;

import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.Comment.exception.CommentErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.infra.comment.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Comment {
    private UUID id;
    private UUID userId;
    private String content;
    private UUID postId;
    private UUID parentId;
    private LocalDateTime createdAt;

    private static String ERROR_CONTENT_IS_NULL = "댓글은 비어있을 수 없습니다.";

    @Builder
    public Comment(UUID id, UUID userId, String content, UUID postId, UUID parentId, LocalDateTime createdAt) {
        validateContent(content);
        
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
        this.createdAt = createdAt;
    }

    // Entity -> Domain 변환 메소드
    public static Comment of(CommentEntity entity) {
        return Comment.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .content(entity.getContent())
                .postId(entity.getPostId())
                .parentId(entity.getParentId())
                .createdAt(entity.getCreatedAt())
                .build();

    }

    public void modifyContent(UUID userId, String content){
        validateOwner(userId);
        this.content = content;
    }

    public void validateOwner(UUID userId){
        if(this.userId != userId)
            throw new BadRequestException("댓글 수정 권한이 없습니다.",CommentErrorCode.NO_AUTHENTICATION);
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new BadRequestException(ERROR_CONTENT_IS_NULL, CommentErrorCode.CONTENT_IS_NULL);
        }
    }
}