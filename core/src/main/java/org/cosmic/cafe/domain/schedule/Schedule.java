package org.cosmic.cafe.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import org.cosmic.cafe.domain.schedule.exception.ScheduleErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;

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

    public static final String ERROR_TITLE_IS_REQUIRED = "제목은 필수 입력값입니다.";
    public static final String ERROR_CONTENT_IS_REQUIRED = "내용은 필수 입력값입니다.";
    public static final String ERROR_SCHEDULE_DATE = "시작일시는 종료일시보다 이전이어야 합니다. 시작일시: %s, 종료일시: %s";

    @Builder
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
        if (title.isEmpty() || title.isBlank()) {
            throw new BadRequestException(ERROR_TITLE_IS_REQUIRED, ScheduleErrorCode.REQUIRED_VALUE);
        }
    }

    private void validateContent(String content) {
        if (content.isEmpty() || content.isBlank()) {
            throw new BadRequestException(ERROR_CONTENT_IS_REQUIRED, ScheduleErrorCode.REQUIRED_VALUE);
        }
    }

    private void validateDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime)) {
            throw new BadRequestException(
                    ERROR_SCHEDULE_DATE.formatted(startDateTime, endDateTime),
                    ScheduleErrorCode.INVALID_DATE_TIME
            );
        }
    }
}
