package org.cosmic.cafe.infra.schedule;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.schedule.Schedule;
import org.cosmic.cafe.domain.schedule.ScheduleRepository;
import org.cosmic.cafe.infra.schedule.entity.ScheduleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ScheduleCoreRepository implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;

    @Override
    public Schedule save(Schedule schedule) {
        ScheduleEntity scheduleEntity = ScheduleEntity.of(schedule);
        ScheduleEntity savedScheduleEntity = scheduleJpaRepository.save(scheduleEntity);
        return Schedule.of(savedScheduleEntity);
    }

    @Override
    public Optional<Schedule> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(UUID id) {

    }
}
