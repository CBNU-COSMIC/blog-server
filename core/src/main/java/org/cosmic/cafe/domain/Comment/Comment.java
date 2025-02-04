package org.cosmic.cafe.domain.Comment;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Comment {
    private UUID id;
    private UUID userId;
    private String content;
    private UUID postId;
    private LocalDateTime createdAt;
    
    public Comment(UUID id, UUID userId, String content, UUID postId, LocalDateTime createdAt) {
        validateContent(content);
        
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    private void validateContent(String content) {

    }
}