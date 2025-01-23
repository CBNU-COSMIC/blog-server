package org.bee.metro.core.post.infra;

import org.bee.metro.core.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByTitle(String title);
}
