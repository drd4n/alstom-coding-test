package com.alstom.codingtest.domain.request.auth;

public record LogInRequest(
        String email,
        String password
) {
}
