package com.enesincekara.nova.calendar.web;

import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record ScheduleCalendarEventRequest(

        @NotBlank(message = "Calendar event title must not be blank")
        String title,

        @NotNull(message = "Calendar event start time must not be null")
        ZonedDateTime startsAt

) {

    public CalendarEventRequest toDomain() {
        return new CalendarEventRequest(title, startsAt);
    }
}