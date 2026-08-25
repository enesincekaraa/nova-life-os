package com.enesincekara.nova.calendar.application;

import com.enesincekara.nova.calendar.application.tool.CalendarTool;
import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ScheduleCalendarEventService {

    private final CalendarTool calendarTool;
    public ScheduleCalendarEventService(CalendarTool calendarTool) {
        this.calendarTool = Objects.requireNonNull(
                calendarTool,
                "CalendarTool must not be null"
        );
    }

    public ScheduledCalendarEvent schedule(CalendarEventRequest request) {
        return calendarTool.schedule(request);
    }


}
