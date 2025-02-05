package org.cosmic.cafe.context;

import org.cosmic.cafe.infra.comment.CommentCoreRepository;
import org.cosmic.cafe.infra.comment.CommentJpaRepository;
import org.cosmic.cafe.infra.schedule.ScheduleCoreRepository;
import org.cosmic.cafe.infra.schedule.ScheduleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({ScheduleCoreRepository.class, CommentCoreRepository.class})
public abstract class RepositoryContext {

    @Autowired
    protected ScheduleJpaRepository scheduleJpaRepository;

    @Autowired
    protected CommentJpaRepository commentJpaRepository;
}
