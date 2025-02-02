package org.cosmic.cafe.dto;

import org.cosmic.cafe.domain.member.Role;

import java.util.UUID;

public record LoginPayload (
        UUID id,
        Role role
) {
}
