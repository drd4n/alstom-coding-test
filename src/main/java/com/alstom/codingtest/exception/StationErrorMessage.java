package com.alstom.codingtest.exception;

public enum StationErrorMessage {

    STATION_NOT_FOUND("Station {replacement} not found"),
    STATION_CONTACT_INFORMATION_NOT_FOUND("Station contact information {replacement} not found");

    private final String message;

    StationErrorMessage(String message) {
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
