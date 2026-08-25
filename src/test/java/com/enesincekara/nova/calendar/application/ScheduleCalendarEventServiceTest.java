package com.enesincekara.nova.calendar.application;

import com.enesincekara.nova.calendar.application.tool.CalendarTool;
import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleCalendarEventServiceTest {

    @Test
    void shouldDelegateSchedulingToCalendarTool() {
        ZonedDateTime startsAt = ZonedDateTime.of(
                2026, 8, 30,
                10, 0, 0, 0,
                ZoneId.of("Europe/Istanbul")
        );

        CalendarEventRequest request =
                new CalendarEventRequest("Proje toplantısı", startsAt);

        ScheduledCalendarEvent expectedEvent = new ScheduledCalendarEvent(
                UUID.randomUUID(),
                request.title(),
                request.startsAt()
        );

        CalendarTool calendarTool = incomingRequest -> {
            assertSame(request, incomingRequest);
            return expectedEvent;
        };

        ScheduleCalendarEventService service =
                new ScheduleCalendarEventService(calendarTool);

        ScheduledCalendarEvent result =service.schedule(request);
        assertSame(expectedEvent, result);

    }


    @Test
    void shouldRejectMissingCalendarTool() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new ScheduleCalendarEventService(null)
        );

        assertEquals(
                "CalendarTool must not be null",
                exception.getMessage()
        );
    }
}
