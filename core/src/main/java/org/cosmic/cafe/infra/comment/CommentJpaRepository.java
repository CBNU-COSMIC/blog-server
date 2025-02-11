package org.cosmic.cafe.infra.comment;

import org.cosmic.cafe.infra.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findByPostId(UUID postId);

    List<CommentEntity> findByParentId(UUID parentId);

    void deleteByPostId(UUID postId);

    void deleteByParentId(UUID parentId);
}
