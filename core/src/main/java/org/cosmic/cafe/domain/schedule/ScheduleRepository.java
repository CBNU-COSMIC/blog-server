package org.cosmic.cafe.domain.schedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> findById(UUID id);
    List<Schedule> findAll();
    void deleteById(UUID id);
}
