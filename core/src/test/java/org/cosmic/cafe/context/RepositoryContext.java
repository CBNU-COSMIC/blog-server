package org.cosmic.cafe.context;

import org.cosmic.cafe.infra.comment.CommentCoreRepository;
import org.cosmic.cafe.infra.comment.CommentJpaRepository;
import org.cosmic.cafe.infra.member.MemberCoreRepository;
import org.cosmic.cafe.infra.member.MemberJpaRepository;
import org.cosmic.cafe.infra.post.PostCoreRepository;
import org.cosmic.cafe.infra.post.PostJpaRepository;
import org.cosmic.cafe.infra.schedule.ScheduleCoreRepository;
import org.cosmic.cafe.infra.schedule.ScheduleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({ScheduleCoreRepository.class, CommentCoreRepository.class, PostCoreRepository.class,
    MemberCoreRepository.class})
public abstract class RepositoryContext {

    @Autowired
    protected ScheduleJpaRepository scheduleJpaRepository;

    @Autowired
    protected CommentJpaRepository commentJpaRepository;
  
    @Autowired
    protected PostJpaRepository postJpaRepository;

    @Autowired
    protected MemberJpaRepository memberJpaRepository;
}
