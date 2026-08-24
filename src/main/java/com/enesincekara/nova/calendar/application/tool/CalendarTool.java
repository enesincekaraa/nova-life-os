package com.enesincekara.nova.calendar.application.tool;

import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;

@FunctionalInterface
public interface CalendarTool {
    ScheduledCalendarEvent schedule(CalendarEventRequest request);
}
