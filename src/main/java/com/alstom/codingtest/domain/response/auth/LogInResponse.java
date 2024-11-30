package com.alstom.codingtest.domain.response.auth;

public record LogInResponse(
    String token,
    String name,
    String email
) {
}
