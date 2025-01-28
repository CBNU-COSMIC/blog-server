package org.cosmic.cafe.infra.schedule;

import org.cosmic.cafe.context.RepositoryContext;
import org.cosmic.cafe.domain.schedule.Color;
import org.cosmic.cafe.domain.schedule.Schedule;
import org.cosmic.cafe.domain.schedule.ScheduleRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ScheduleRepositoryTest extends RepositoryContext {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Nested
    class save_메서드는 {

        @Test
        void 일정_객체를_저장한다() {
            // given
            String title = "title";
            String content = "content";
            UUID memberId = UUID.randomUUID();
            LocalDateTime startDateTime = LocalDateTime.now();
            LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);
            Color color = Color.RED;

            // when
            Schedule savedSchedule = scheduleRepository.save(Schedule.builder()
                    .title(title)
                    .content(content)
                    .memberId(memberId)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .color(color)
                    .build());

            // then
            assertAll(
                    () -> assertThat(savedSchedule.getId()).isNotNull(),
                    () -> assertThat(savedSchedule.getTitle()).isEqualTo(title),
                    () -> assertThat(savedSchedule.getContent()).isEqualTo(content),
                    () -> assertThat(savedSchedule.getMemberId()).isEqualTo(memberId),
                    () -> assertThat(savedSchedule.getStartDateTime()).isEqualTo(startDateTime),
                    () -> assertThat(savedSchedule.getEndDateTime()).isEqualTo(endDateTime),
                    () -> assertThat(savedSchedule.getColor()).isEqualTo(color)
            );
        }

        @Test
        void 존재하는_일정_객체는_수정한다() {
            // given
            String title = "title";
            String updatedTitle = "updated title";
            String content = "content";
            UUID memberId = UUID.randomUUID();
            LocalDateTime startDateTime = LocalDateTime.now();
            LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);
            Color color = Color.RED;

            Schedule savedSchedule = scheduleRepository.save(Schedule.builder()
                    .title(title)
                    .content(content)
                    .memberId(memberId)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .color(color)
                    .build());


            // when
            Schedule updatedSchedule = scheduleRepository.save(Schedule.builder()
                    .id(savedSchedule.getId())
                    .title(updatedTitle)
                    .content(content)
                    .memberId(memberId)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .color(color)
                    .build());


            // then
            assertThat(savedSchedule.getId()).isEqualTo(updatedSchedule.getId());
            assertThat(updatedSchedule.getTitle()).isEqualTo(updatedTitle);
        }
    }

    @Nested
    class findById_메서드는 {

        @Test
        void 해당_아이디가_존재하면_일정_객체를_반환한다() {
            // given
            String title = "title";
            String content = "content";
            UUID memberId = UUID.randomUUID();
            LocalDateTime startDateTime = LocalDateTime.now();
            LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);
            Color color = Color.RED;

            Schedule savedSchedule = scheduleRepository.save(Schedule.builder()
                    .title(title)
                    .content(content)
                    .memberId(memberId)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .color(color)
                    .build());

            // when
            Schedule foundSchedule = scheduleRepository.findById(savedSchedule.getId()).orElse(null);

            // then
            assertAll(
                    () -> assertThat(foundSchedule).isNotNull(),
                    () -> assertThat(foundSchedule.getId()).isEqualTo(savedSchedule.getId())
            );
        }

        @Test
        void 해당_아이디가_존재하지_않으면_null을_반환한다() {
            // given
            UUID id = UUID.randomUUID();

            // when
            Schedule foundSchedule = scheduleRepository.findById(id).orElse(null);

            // then
            assertThat(foundSchedule).isNull();
        }
    }
}
