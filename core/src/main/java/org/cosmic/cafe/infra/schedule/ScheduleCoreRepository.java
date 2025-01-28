package org.cosmic.cafe.infra.schedule;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.schedule.Schedule;
import org.cosmic.cafe.domain.schedule.ScheduleRepository;
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
        return null;
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
