package org.cosmic.cafe.infra.member;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.member.Member;
import org.cosmic.cafe.domain.member.MemberRepository;
import org.cosmic.cafe.infra.member.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberCoreRepository implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = MemberEntity.of(member);
        MemberEntity savedMemberEntity = memberJpaRepository.save(memberEntity);
        return Member.of(savedMemberEntity);
    }


    @Override
    public Optional<Member> findById(UUID id) {
        Optional<MemberEntity> memberEntity = memberJpaRepository.findById(id);
        return memberEntity.map(Member::of);
    }

    @Override
    public Optional<Member> findByMemberId(String memberId){
        return memberJpaRepository.findByMemberId(memberId).map(Member::of);
    }

    @Override
    public List<Member> findAll() {
        List<MemberEntity> memberEntities = memberJpaRepository.findAll();

        return memberEntities.stream()
            .map(Member::of)
            .toList();
    }

    @Override
    public void deleteById(UUID id) {

        memberJpaRepository.deleteById(id);
    }
}


