package org.cosmic.cafe.infra.schedule;

import org.cosmic.cafe.infra.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {
}
