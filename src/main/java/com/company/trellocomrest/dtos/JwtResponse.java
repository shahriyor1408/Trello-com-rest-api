package com.company.trellocomrest.dtos;

public record JwtResponse(
        String accessToken,
        String refreshToken,
        String tokenType) {
}
