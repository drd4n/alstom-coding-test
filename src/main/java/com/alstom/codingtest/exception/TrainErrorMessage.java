package com.alstom.codingtest.exception;

public enum TrainErrorMessage {

    TRAIN_NOT_FOUND("Train {replacement} not found"),
    TRAIN_WERE_BOOKED("Cannot delete Train {replacement} because it was already booked"),;

    private final String message;

    TrainErrorMessage(String message) {
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
