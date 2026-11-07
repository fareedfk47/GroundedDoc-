package com.hostlelo.docReader.dto;

import com.hostlelo.docReader.entity.Role;

public record RegisterUserRequest(
        String username,
        String email,
        String password
) {
}
