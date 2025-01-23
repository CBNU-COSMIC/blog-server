package org.cosmic.cafe.infra;

import org.cosmic.cafe.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByTitle(String title);
}
