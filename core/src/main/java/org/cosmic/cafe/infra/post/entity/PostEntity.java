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

    private String title;

    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    private Long hits;

    protected PostEntity(){}

    @Builder
    public PostEntity(UUID id, String title, String content, LocalDateTime createdAt, Long hits) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
    }

    public static PostEntity from(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .hits(post.getHits())
                .build();
    }
}
