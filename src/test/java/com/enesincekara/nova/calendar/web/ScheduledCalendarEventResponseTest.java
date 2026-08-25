package com.enesincekara.nova.calendar.web;

import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduledCalendarEventResponseTest {

    @Test
    void shouldConvertDomainEventToHttpResponse() {
        UUID eventId = UUID.randomUUID();

        ZonedDateTime startsAt = ZonedDateTime.parse(
                "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
        );

        ScheduledCalendarEvent domainEvent =
                new ScheduledCalendarEvent(
                        eventId,
                        "Proje toplantısı",
                        startsAt
                );

        ScheduledCalendarEventResponse response =
                ScheduledCalendarEventResponse.from(domainEvent);

        assertEquals(eventId, response.eventId());
        assertEquals("Proje toplantısı", response.title());
        assertEquals(startsAt, response.startsAt());
    }
}