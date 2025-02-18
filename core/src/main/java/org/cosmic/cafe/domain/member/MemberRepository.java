package org.cosmic.cafe.domain.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    Optional<Member> findById(UUID id);
    Optional<Member> findByMemberId(String memberId);

    Member save(Member member);

    void deleteById(UUID id);

    List<Member> findAll();
}
