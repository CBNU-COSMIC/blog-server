package org.cosmic.cafe.application.schedule;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.schedule.dto.ScheduleCreationPayload;
import org.cosmic.cafe.domain.schedule.Schedule;
import org.cosmic.cafe.domain.schedule.ScheduleRepository;
import org.cosmic.cafe.domain.schedule.exception.ScheduleErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.exception.type.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void createSchedule(UUID memberId, ScheduleCreationPayload schedulePayload) {
        Schedule schedule = new Schedule(
                null,
                schedulePayload.title(),
                schedulePayload.content(),
                memberId,
                schedulePayload.startDateTime(),
                schedulePayload.endDateTime(),
                schedulePayload.color()
        );

        scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getSchedule(UUID scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("해당 일정이 존재하지 않습니다.", ScheduleErrorCode.NOT_FOUND));
    }

    @Transactional
    public void deleteSchedule(UUID memberId, UUID scheduleId, boolean isAdmin) {
        Schedule schedule = this.getSchedule(scheduleId);
        verifyDeletionPermission(memberId, isAdmin, schedule);
        scheduleRepository.deleteById(scheduleId);
    }

    private static void verifyDeletionPermission(UUID memberId, boolean isAdmin, Schedule schedule) {
        boolean deletePermission = isAdmin || schedule.getMemberId().equals(memberId);

        if (!deletePermission) {
            throw new BadRequestException(
                    "해당 일정을 삭제할 권한이 없습니다. 해당 일정 소유자: %s, 현재 소유자: %s".formatted(schedule.getMemberId(), memberId),
                    ScheduleErrorCode.DELETE_PERMISSION_DENIED
            );
        }
    }
}
