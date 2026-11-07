package com.hostlelo.groundeddoc.dto;

public record LoginResponse(
        String accessToken,
        UserDto user
) {
}
