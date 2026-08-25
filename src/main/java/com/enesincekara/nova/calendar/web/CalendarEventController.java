package com.enesincekara.nova.calendar.web;


import com.enesincekara.nova.calendar.application.ScheduleCalendarEventService;
import com.enesincekara.nova.calendar.domain.ScheduledCalendarEvent;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/calendar/events")
public class CalendarEventController {

    private final ScheduleCalendarEventService service;

    public CalendarEventController(ScheduleCalendarEventService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduledCalendarEventResponse schedule(
           @Valid @RequestBody ScheduleCalendarEventRequest request
    ){
        ScheduledCalendarEvent scheduledCalendarEvent=
                service.schedule(request.toDomain());

        return ScheduledCalendarEventResponse.from(scheduledCalendarEvent);
    }
}
