package com.alstom.codingtest.exception;

public enum AuthErrorMessage {

    EMAIL_DUPLICATED("Email {replacement} already exists"),
    USER_NOT_FOUND("User with email {replacement} not found"),;

    private final String message;

    AuthErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(String... replacements) {
        String message = this.message;
        for (String replacement : replacements) {
            message = message.replaceFirst("\\{replacement\\}", replacement);
        }
        return message;
    }
}
