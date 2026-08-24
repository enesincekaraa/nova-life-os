package com.enesincekara.nova.calendar.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public record ScheduledCalendarEvent(
        UUID eventId,
        String title,
        ZonedDateTime startsAt
) {
}
