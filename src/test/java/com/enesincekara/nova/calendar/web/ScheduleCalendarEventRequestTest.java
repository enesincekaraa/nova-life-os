package com.enesincekara.nova.calendar.web;

import com.enesincekara.nova.calendar.domain.CalendarEventRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleCalendarEventRequestTest {

    private final Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();


    @Test
    void shouldConvertHttpRequestToDomainRequest() {
        ZonedDateTime startsAt = ZonedDateTime.parse(
                "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
        );

        ScheduleCalendarEventRequest httpRequest =
                new ScheduleCalendarEventRequest(
                        "Proje toplantısı",
                        startsAt
                );

        CalendarEventRequest domainRequest =
                httpRequest.toDomain();


        assertEquals("Proje toplantısı", domainRequest.title());
        assertEquals(startsAt, domainRequest.startsAt());
    }

    @Test
    void shouldRejectBlankTitle() {
        ScheduleCalendarEventRequest request =
                new ScheduleCalendarEventRequest(
                        " ",
                        ZonedDateTime.parse(
                                "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                        )
                );

        Set<ConstraintViolation<ScheduleCalendarEventRequest>> violations =
                validator.validate(request);

        assertTrue(
                violations.stream()
                        .anyMatch(violation ->
                                violation.getPropertyPath()
                                        .toString()
                                        .equals("title")
                        )
        );
    }

    @Test
    void shouldRejectMissingStartTime() {
        ScheduleCalendarEventRequest request =
                new ScheduleCalendarEventRequest(
                        "Proje toplantısı",
                        null
                );

        Set<ConstraintViolation<ScheduleCalendarEventRequest>> violations =
                validator.validate(request);

        assertTrue(
                violations.stream()
                        .anyMatch(violation ->
                                violation.getPropertyPath()
                                        .toString()
                                        .equals("startsAt")
                        )
        );
    }

}


