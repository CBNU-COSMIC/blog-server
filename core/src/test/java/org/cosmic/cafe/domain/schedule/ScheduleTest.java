package org.cosmic.cafe.domain.schedule;

import org.cosmic.cafe.exception.type.BadRequestException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScheduleTest {

    @Nested
    class 일정_도메인은 {

        @Test
        void 정상적인_인자가_들어오면_객체가_생성된다() {
            // given
            UUID id = UUID.randomUUID();
            String title = "title";
            String content = "content";
            UUID memberId = UUID.randomUUID();
            LocalDateTime startDateTime = LocalDateTime.now();
            LocalDateTime endDateTime = startDateTime.plusDays(1);
            Color color = Color.RED;

            // when
            Schedule schedule = Schedule.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .memberId(memberId)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .color(color)
                    .build();

            // then
            assertThat(schedule.getId()).isEqualTo(id);
            assertThat(schedule.getTitle()).isEqualTo(title);
            assertThat(schedule.getContent()).isEqualTo(content);
            assertThat(schedule.getMemberId()).isEqualTo(memberId);
            assertThat(schedule.getStartDateTime()).isEqualTo(startDateTime);
            assertThat(schedule.getEndDateTime()).isEqualTo(endDateTime);
            assertThat(schedule.getColor()).isEqualTo(color);
        }

        @ParameterizedTest
        @MethodSource("generateInvalidScheduleArguments")
        void 잘못된_인자가_들어오면_예외가_발생한다(String title, String content, LocalDateTime startDateTime, LocalDateTime endDateTime) {
            // given
            UUID id = UUID.randomUUID();
            UUID memberId = UUID.randomUUID();
            Color color = Color.RED;

            // expect
            assertThatThrownBy(() -> Schedule.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .memberId(memberId)
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .color(color)
                    .build())
                    .isInstanceOf(BadRequestException.class);
        }

        private static Stream<Arguments> generateInvalidScheduleArguments() {
            return Stream.of(
                    Arguments.of(null, "content", LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                    Arguments.of("", "content", LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                    Arguments.of("title", null, LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                    Arguments.of("title", "", LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                    Arguments.of("title", "content", LocalDateTime.now().plusDays(1), LocalDateTime.now())
            );
        }

    }
}