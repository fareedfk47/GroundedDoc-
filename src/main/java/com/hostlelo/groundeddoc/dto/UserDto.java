package com.hostlelo.groundeddoc.dto;

import com.hostlelo.groundeddoc.entity.Role;

public record UserDto(
        Long id,
        String username,
        String email,
        Role role
) {
}
