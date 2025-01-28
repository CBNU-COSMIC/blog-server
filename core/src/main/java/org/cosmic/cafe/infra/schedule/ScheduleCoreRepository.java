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
        Optional<ScheduleEntity> scheduleEntity = scheduleJpaRepository.findById(id);
        return scheduleEntity.map(Schedule::of);
    }

    @Override
    public List<Schedule> findAll() {
        List<ScheduleEntity> scheduleEntities = scheduleJpaRepository.findAll();

        return scheduleEntities.stream()
                .map(Schedule::of)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        scheduleJpaRepository.deleteById(id);
    }
}
