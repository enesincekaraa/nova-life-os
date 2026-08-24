package com.enesincekara.nova.calendar.infrastructure.memory;

import com.enesincekara.nova.calendar.application.tool.CalendarTool;
import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class InMemoryCalendarTool implements CalendarTool {

    private final Map<UUID, ScheduledCalendarEvent> eventsById = new HashMap<>();


    @Override
    public ScheduledCalendarEvent schedule(CalendarEventRequest request) {

        UUID eventId = UUID.randomUUID();

        ScheduledCalendarEvent scheduledEvent = new ScheduledCalendarEvent(eventId, request.title(), request.startsAt());
        eventsById.put(eventId, scheduledEvent);
        return scheduledEvent;
    }

    public Optional<ScheduledCalendarEvent> findById(UUID eventId) {
        return Optional.ofNullable(eventsById.get(eventId));
    }
}
