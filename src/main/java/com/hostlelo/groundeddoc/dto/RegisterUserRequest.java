package com.hostlelo.groundeddoc.dto;

import com.hostlelo.groundeddoc.entity.Role;

public record RegisterUserRequest(
        String username,
        String email,
        String password
) {
}
