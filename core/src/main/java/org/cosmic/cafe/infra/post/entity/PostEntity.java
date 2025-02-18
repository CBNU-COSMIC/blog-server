package org.cosmic.cafe.infra.post.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.post.Post;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "POST")
@Getter
public class PostEntity {

    @Id @GeneratedValue
    private UUID id;

    private UUID memberId;

    private String boardId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private Long hits;

    protected PostEntity(){}

    @Builder
    public PostEntity(UUID id, UUID memberId, String boardId, String title, String content, LocalDateTime createdAt, Long hits) {

        this.id = id;
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.hits = hits;
    }

    public static PostEntity from(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .memberId(post.getMemberId())
                .boardId(post.getBoardId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .hits(post.getHits())
                .build();
    }
}
