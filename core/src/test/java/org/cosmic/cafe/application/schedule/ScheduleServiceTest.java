package org.cosmic.cafe.application.schedule;

import org.cosmic.cafe.application.schedule.dto.ScheduleCreationPayload;
import org.cosmic.cafe.context.ServiceContext;
import org.cosmic.cafe.domain.schedule.Color;
import org.cosmic.cafe.domain.schedule.Schedule;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.exception.type.NotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class ScheduleServiceIntegrationTest extends ServiceContext {

    @Nested
    class 일정_생성_테스트 {

        @Test
        void 일정을_정상적으로_생성할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            ScheduleCreationPayload payload = new ScheduleCreationPayload(
                    "Meeting",
                    "Project discussion",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(2),
                    Color.BLUE
            );

            // when
            scheduleService.createSchedule(memberId, payload);

            // then
            List<Schedule> schedules = scheduleRepository.findAll();
            assertThat(schedules).hasSize(1);
            assertThat(schedules.get(0).getTitle()).isEqualTo("Meeting");
        }
    }

    @Nested
    class 일정_조회_테스트 {

        @Test
        void 전체_일정을_조회할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            scheduleRepository.save(new Schedule(
                    null, "Title1", "Content1", memberId,
                    LocalDateTime.now(), LocalDateTime.now().plusDays(1), Color.RED
            ));
            scheduleRepository.save(new Schedule(
                    null, "Title2", "Content2", memberId,
                    LocalDateTime.now(), LocalDateTime.now().plusDays(2), Color.BLUE
            ));

            // when
            List<Schedule> schedules = scheduleService.getSchedules();

            // then
            assertThat(schedules).hasSize(2);
        }

        @Test
        void 특정_일정을_조회할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            Schedule schedule = scheduleRepository.save(new Schedule(
                    null, "Title1", "Content1", memberId,
                    LocalDateTime.now(), LocalDateTime.now().plusDays(1), Color.RED
            ));

            // when
            Schedule foundSchedule = scheduleService.getSchedule(schedule.getId());

            // then
            assertThat(foundSchedule.getId()).isEqualTo(schedule.getId());
            assertThat(foundSchedule.getTitle()).isEqualTo("Title1");
        }

        @Test
        void 존재하지_않는_일정을_조회하면_예외가_발생한다() {
            // expect
            assertThatThrownBy(() -> scheduleService.getSchedule(UUID.randomUUID()))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessageContaining("해당 일정이 존재하지 않습니다.");
        }
    }

    @Nested
    class 일정_삭제_테스트 {

        @Test
        void 본인이_일정을_삭제할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            Schedule schedule = scheduleRepository.save(new Schedule(
                    null, "Title1", "Content1", memberId,
                    LocalDateTime.now(), LocalDateTime.now().plusDays(1), Color.GREEN
            ));

            // when
            scheduleService.deleteSchedule(memberId, schedule.getId(), false);

            // then
            assertThat(scheduleRepository.findById(schedule.getId())).isEmpty();
        }

        @Test
        void 관리자는_타인의_일정도_삭제할_수_있다() {
            // given
            UUID memberId = UUID.randomUUID();
            UUID anotherMemberId = UUID.randomUUID();
            Schedule schedule = scheduleRepository.save(new Schedule(
                    null, "Title1", "Content1", memberId,
                    LocalDateTime.now(), LocalDateTime.now().plusDays(1), Color.GREEN
            ));

            // when
            scheduleService.deleteSchedule(anotherMemberId, schedule.getId(), true);

            // then
            assertThat(scheduleRepository.findById(schedule.getId())).isEmpty();
        }

        @Test
        void 본인이_아닌_경우_삭제할_수_없다() {
            // given
            UUID memberId = UUID.randomUUID();
            UUID anotherMemberId = UUID.randomUUID();
            Schedule schedule = scheduleRepository.save(new Schedule(
                    null, "Title1", "Content1", memberId,
                    LocalDateTime.now(), LocalDateTime.now().plusDays(1), Color.GREEN
            ));

            // expect
            assertThatThrownBy(() -> scheduleService.deleteSchedule(anotherMemberId, schedule.getId(), false))
                    .isInstanceOf(BadRequestException.class)
                    .hasMessageContaining("해당 일정을 삭제할 권한이 없습니다.");
        }

        @Test
        void 존재하지_않는_일정을_삭제하려고_하면_예외가_발생한다() {
            // expect
            assertThatThrownBy(() -> scheduleService.deleteSchedule(UUID.randomUUID(), UUID.randomUUID(), false))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessageContaining("해당 일정이 존재하지 않습니다.");
        }
    }
}
