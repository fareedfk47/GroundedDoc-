package com.hostlelo.docReader.dto;

public record LoginResponse(
        String accessToken,
        UserDto user
) {
}
