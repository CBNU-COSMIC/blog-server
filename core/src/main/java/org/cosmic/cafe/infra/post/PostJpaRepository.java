package org.cosmic.cafe.infra.post;

import org.cosmic.cafe.infra.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostJpaRepository extends JpaRepository<PostEntity, UUID> {
    List<PostEntity> findByTitle(String title);

    List<PostEntity> findByTitleContaining(String title);

    List<PostEntity> findByBoardId(String id);
}
