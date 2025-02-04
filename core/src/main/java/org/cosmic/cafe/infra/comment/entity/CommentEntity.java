package org.cosmic.cafe.infra.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public CommentEntity(UUID id, UUID userId, String content, UUID postId, UUID parentId) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
    }
}
