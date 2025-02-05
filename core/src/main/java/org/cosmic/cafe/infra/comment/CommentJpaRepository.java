package org.cosmic.cafe.infra.comment;

import org.cosmic.cafe.infra.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
}
