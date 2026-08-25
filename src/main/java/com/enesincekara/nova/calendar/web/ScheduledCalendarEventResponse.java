package com.enesincekara.nova.calendar.web;

import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;

import java.time.ZonedDateTime;
import java.util.UUID;

public record ScheduledCalendarEventResponse(
        UUID eventId,
        String title,
        ZonedDateTime startsAt
) {

    public static ScheduledCalendarEventResponse from(
            ScheduledCalendarEvent domainEvent
    ) {
        return new ScheduledCalendarEventResponse(
                domainEvent.eventId(),
                domainEvent.title(),
                domainEvent.startsAt()
        );
    }
}