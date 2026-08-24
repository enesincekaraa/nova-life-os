package com.enesincekara.nova.calendar.domain;

import java.time.ZonedDateTime;

public record CalendarEventRequest(
        String title,
        ZonedDateTime startsAt
) {

    public CalendarEventRequest {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Calendar event title must not be blank"
            );
        }
        if (startsAt == null) {
            throw new IllegalArgumentException(
                    "Calendar event start time must not be null"
            );
        }
    }
}