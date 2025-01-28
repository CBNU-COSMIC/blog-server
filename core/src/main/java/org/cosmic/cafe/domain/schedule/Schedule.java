package org.cosmic.cafe.domain.schedule;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Schedule {

    private final UUID id;
    private final String title;
    private final String content;
    private final UUID memberId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final Color color;

    public Schedule(UUID id, String title, String content, UUID memberId, LocalDateTime startDateTime, LocalDateTime endDateTime, Color color) {
        validateTitle(title);
        validateContent(content);
        validateDateTime(startDateTime, endDateTime);

        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.color = color;
    }

    private void validateTitle(String title) {

    }

    private void validateContent(String content) {

    }

    private void validateDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {

    }
}
