package org.cosmic.cafe.application.member;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.member.dto.MemberCreationPayload;
import org.cosmic.cafe.application.member.dto.MemberDetailResponse;
import org.cosmic.cafe.application.member.dto.MemberUpdateRequest;
import org.cosmic.cafe.domain.member.Member;
import org.cosmic.cafe.domain.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberCreationPayload memberPayload){
        validateDuplicateMember(memberPayload.memberId());
        validateDuplicateNickname(memberPayload.nickname());
        Member member = new Member(
            null,
            memberPayload.name(),
            memberPayload.memberId(),
            memberPayload.nickname(),
            encryptPassword(memberPayload.password()),
            memberPayload.role(),
            memberPayload.avatar(),
            memberPayload.phoneNumber(),
            memberPayload.studentNumber(),
            memberPayload.birth(),
            memberPayload.email()
        );
        memberRepository.save(member);
    }

    public void validateDuplicateMember(String memberId){
        memberRepository.findByMemberId(memberId)
            .ifPresent(member -> {
                throw new IllegalArgumentException("이미 존재하는 회원 ID입니다.");
            });
    }
    public void validateDuplicateNickname(String nickname){
        memberRepository.findByNickname(nickname)
            .ifPresent(member -> {
                throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
            });
    }

    private String encryptPassword(String password){

        return passwordEncoder.encode(password);
    }

    @Transactional
    public void withdrawMember(UUID id){
        memberRepository.deleteById(id);
    }

    @Transactional
    public void updateMember(UUID id, MemberUpdateRequest memberUpdateRequest){
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        Member member = optionalMember.get();

        if(!member.getNickname().equals(memberUpdateRequest.getNickname())){
            validateDuplicateNickname(memberUpdateRequest.getNickname());
        }

        member.setName(memberUpdateRequest.getName());
        member.setNickname(memberUpdateRequest.getNickname());
        member.setPhoneNumber(memberUpdateRequest.getPhoneNumber());
        member.setBirth(memberUpdateRequest.getBirth());
        member.setEmail(memberUpdateRequest.getEmail());

        //memberRepository.merge(member);
        memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findByNickname(String nickname){
        return memberRepository.findByNickname(nickname);
    }

    @Transactional
    public void updateMemberRole(String nickname, String role) {
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        if (optionalMember.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        Member member = optionalMember.get();

        member.setRole(role); // 역할만 업데이트
        memberRepository.save(member);
    }

    public MemberDetailResponse getMemberDetail(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return MemberDetailResponse.of(member);
    }


}
