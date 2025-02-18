package org.cosmic.cafe.application.post.dto;

import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.post.Post;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PostListResponse {
    private UUID postId;
    private String title;
    private String author;
    private LocalDateTime date;
    private long hits;

    public static PostListResponse of(Post post, String author) {
        return PostListResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .author(author)
                .date(post.getCreatedAt())
                .hits(post.getHits())
                .build();
    }
}
