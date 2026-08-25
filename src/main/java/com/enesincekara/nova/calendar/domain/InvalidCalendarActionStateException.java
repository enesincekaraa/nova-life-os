package com.enesincekara.nova.calendar.domain;

public final class InvalidCalendarActionStateException extends RuntimeException {
    public InvalidCalendarActionStateException(CalendarActionStatus actualStatus) {
        super(
                "Calendar action must be pending approval but was "
                        + actualStatus
        );
    }
}
