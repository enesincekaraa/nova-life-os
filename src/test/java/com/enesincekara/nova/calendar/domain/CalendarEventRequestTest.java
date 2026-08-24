package com.enesincekara.nova.calendar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalendarEventRequestTest {

    @Test
    void shouldCreateCalendarEventRequestWithTitleAndStartTime() {
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


        assertEquals("Dişçi randevusu",request.title());
        assertEquals(startsAt, request.startsAt());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"","  "})
    void shouldRejectBlankTitle(String title) {

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

        assertThrows(
                IllegalArgumentException.class,
                () -> new CalendarEventRequest(title, startsAt)
        );
    }

    @Test
    void shouldRejectMissingStartTime() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CalendarEventRequest(
                        "Dişçi randevusu",
                        null
                )
        );
    }
}
