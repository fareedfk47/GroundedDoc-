package com.hostlelo.docReader.dto;

import com.hostlelo.docReader.entity.Role;

public record UserDto(
        Long id,
        String username,
        String email,
        Role role
) {
}
