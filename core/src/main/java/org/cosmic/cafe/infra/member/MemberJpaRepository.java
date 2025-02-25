package org.cosmic.cafe.infra.member;

import java.util.Optional;
import java.util.UUID;
import org.cosmic.cafe.infra.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByMemberId(String memberId);

    Optional<MemberEntity> findByNickname(String nickname);
}
