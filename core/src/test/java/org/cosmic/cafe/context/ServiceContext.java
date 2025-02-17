package org.cosmic.cafe.context;

import org.cosmic.cafe.application.comment.CommentService;
import org.cosmic.cafe.application.schedule.ScheduleService;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.cosmic.cafe.domain.schedule.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class ServiceContext {

    @Autowired
    DatabaseCleaner databaseCleaner;

    @Autowired
    protected ScheduleRepository scheduleRepository;

    @Autowired
    protected ScheduleService scheduleService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected CommentRepository commentRepository;

    @AfterEach
    void clear() {
        databaseCleaner.clear();
    }
}
