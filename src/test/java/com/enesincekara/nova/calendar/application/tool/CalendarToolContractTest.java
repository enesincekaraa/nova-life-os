package com.enesincekara.nova.calendar.application.tool;

import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarToolContractTest {

    @Test
    void shouldReturnScheduledCalendarEvent() {
        ZonedDateTime startsAt = ZonedDateTime.of(2026, 8, 29, 14, 30, 0, 0, ZoneId.of("Europe/Istanbul"));

        CalendarEventRequest request = new CalendarEventRequest("Dişçi randevusu", startsAt);

        UUID eventId = UUID.randomUUID();

        CalendarTool calendarTool = incomingRequest -> new ScheduledCalendarEvent(eventId, incomingRequest.title(), incomingRequest.startsAt());

        ScheduledCalendarEvent result = calendarTool.schedule(request);

        assertEquals(eventId, result.eventId());
        assertEquals("Dişçi randevusu", result.title());
        assertEquals(startsAt, result.startsAt());
    }
}
