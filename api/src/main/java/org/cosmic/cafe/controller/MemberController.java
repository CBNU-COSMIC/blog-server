package org.cosmic.cafe.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.member.MemberService;
import org.cosmic.cafe.application.member.dto.MemberCreationPayload;
import org.cosmic.cafe.application.member.dto.MemberDetailResponse;
import org.cosmic.cafe.application.member.dto.MemberListResponse;
import org.cosmic.cafe.application.member.dto.MemberUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreationPayload memberCreationPayload) {
        memberService.createMember(memberCreationPayload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<MemberDetailResponse> getMember(@PathVariable String nickname) {
        MemberDetailResponse response = memberService.getMemberDetail(nickname);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberListResponse>> getMembers() {
        List<MemberListResponse> response = memberService.findAll().stream()
            .map(member -> MemberListResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .build())
            .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(id, memberUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID id) {
        memberService.withdrawMember(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{nickname}/role")
    public ResponseEntity<Void> updateMemberRole(
        @PathVariable String nickname,
        @RequestParam String role) {

        memberService.updateMemberRole(nickname, role);
        return ResponseEntity.ok().build();
    }
}

