package org.cosmic.cafe.infra.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cosmic.cafe.domain.Comment.Comment;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "Comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;

    private String content;

    private UUID postId;

    private UUID parentId;

    private LocalDateTime createdAt;

    @Builder
    public CommentEntity(UUID id, UUID userId, String content, UUID postId, UUID parentId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
        this.createdAt = createdAt;
    }

    // 도메인 -> 엔티티 변환 메소드
    public static CommentEntity of(Comment comment) {
        return CommentEntity.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .postId(comment.getPostId())
                .parentId(comment.getParentId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
