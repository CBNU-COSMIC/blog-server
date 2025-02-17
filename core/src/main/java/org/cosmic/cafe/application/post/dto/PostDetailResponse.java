package org.cosmic.cafe.application.post.dto;

import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.post.Post;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDetailResponse {
    private String title;
    private String content;
    private LocalDateTime date;
    private long hits;
    private String author;

    public static PostDetailResponse of(Post post, String author) {
        return PostDetailResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .date(post.getCreatedAt())
                .hits(post.getHits())
                .author(author)
                .build();
    }
}
