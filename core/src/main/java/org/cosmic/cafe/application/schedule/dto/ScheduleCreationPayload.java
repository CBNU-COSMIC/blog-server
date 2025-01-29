package org.cosmic.cafe.application.schedule.dto;

import org.cosmic.cafe.domain.schedule.Color;

import java.time.LocalDateTime;

public record ScheduleCreationPayload(
        String title,
        String content,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Color color
) {
}
