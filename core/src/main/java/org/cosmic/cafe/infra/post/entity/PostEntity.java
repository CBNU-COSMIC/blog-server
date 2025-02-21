package org.cosmic.cafe.infra.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.post.Post;

import java.util.UUID;

@Entity
@Table(name = "POST")
@Getter
public class PostEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String boardId;

    private String title;

    private String content;

    private Long hits;

    protected PostEntity() {
    }

    @Builder
    public PostEntity(UUID id, String boardId, String title, String content, Long hits) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.hits = hits;
    }

    public static PostEntity from(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .boardId(post.getBoardId())
                .title(post.getTitle())
                .content(post.getContent())
                .hits(post.getHits())
                .build();
    }
}
