package org.cosmic.cafe.context;

import org.cosmic.cafe.application.post.PostService;
import org.cosmic.cafe.application.schedule.ScheduleService;
import org.cosmic.cafe.domain.post.PostRepository;
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
    protected PostRepository postRepository;

    @Autowired
    protected PostService postService;

    @AfterEach
    void clear() {
        databaseCleaner.clear();
    }
}
