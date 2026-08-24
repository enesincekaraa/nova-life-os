package com.enesincekara.nova.calendar.infrastructure.memory;

import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryCalendarToolTest {

    @Test
    void shouldScheduleAndStoreCalendarEvent() {
        ZonedDateTime startsAt = ZonedDateTime.of(
                2026,
                8,
                29,
                14,
                30,
                0,
                0,
                ZoneId.of("Europe/Istanbul")
        );

        CalendarEventRequest request =
                new CalendarEventRequest("Dişçi randevusu", startsAt);

        InMemoryCalendarTool calendarTool =
                new InMemoryCalendarTool();


        var result = calendarTool.schedule(request);

        assertNotNull(result.eventId());
        assertEquals(request.title(), result.title());
        assertEquals(request.startsAt(), result.startsAt());

        assertEquals(
                result,
                calendarTool.findById(result.eventId()).orElseThrow()
        );
    }
}
