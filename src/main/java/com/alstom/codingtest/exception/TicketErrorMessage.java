package com.alstom.codingtest.exception;

public enum TicketErrorMessage {

    TICKET_NOT_FOUND("Ticket {replacement} not found"),
    TRAIN_FULLY_BOOKED("Train {replacement} is fully booked"),
    SEAT_NOT_AVAILABLE("Seat {replacement} is not available"),
    INVALID_DEPARTURE_DATE("Departure date cannot be in the past"),;

    private final String message;

    TicketErrorMessage(String message) {
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
