package org.cosmic.cafe.domain.post;

import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.post.exception.PostErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.infra.post.entity.PostEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Post {

    private UUID id;
    private UUID memberId;
    private String boardId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long hits;

    private static String ERROR_TITLE_IS_BLANK = "제목은 비어있을 수 없습니다.";
    private static String ERROR_CONTENT_IS_BLANK = "본문은 비어있을 수 없습니다.";

    @Builder
    public Post(UUID id, UUID memberId, String boardId, String title, String content, LocalDateTime createdAt, Long hits){
        validateTitle(title);
        validateContent(content);
        validateBoardId(boardId);
        this.id = id;
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = makeValidTitle(title);
        this.content = makeValidContent(content);
        this.createdAt = createdAt;
        this.hits =hits;
    }

    public static Post of(PostEntity entity){
        return Post.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .boardId(entity.getBoardId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .hits(entity.getHits())
                .build();
    }

    public boolean isNotWritten(UUID memberId) {
        return !this.memberId.equals(memberId);
    }

    private void validateBoardId(String boardId){
        if(boardId == null)
            throw new BadRequestException(ERROR_CONTENT_IS_BLANK,PostErrorCode.ARGUMENT_IS_NULL);
    }

    private void validateTitle(String title){
        if(title == null || title.isBlank())
            throw new BadRequestException(ERROR_TITLE_IS_BLANK, PostErrorCode.ARGUMENT_IS_NULL);
    }

    private void validateContent(String content){
        if(content == null || content.isBlank())
            throw new BadRequestException(ERROR_CONTENT_IS_BLANK,PostErrorCode.ARGUMENT_IS_NULL);
    }

    private String makeValidTitle(String title){
        return title.replace("<","&lt;").replace(">","&gt;");
    }

    private String makeValidContent(String content){
        return content.replace("<","&lt;").replace(">","&gt;");
    }
}
