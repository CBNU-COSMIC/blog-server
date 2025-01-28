package org.cosmic.cafe.infra.schedule.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cosmic.cafe.domain.schedule.Color;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "Schedule")
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String content;

    private UUID memberId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Color color;

    @Builder
    public ScheduleEntity(UUID id, String title, String content, UUID memberId, LocalDateTime startDateTime, LocalDateTime endDateTime, Color color) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.color = color;
    }
}
