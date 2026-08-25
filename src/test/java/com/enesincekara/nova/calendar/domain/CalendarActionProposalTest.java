package com.enesincekara.nova.calendar.domain;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CalendarActionProposalTest {

    @Test
    void shouldStartInPendingApprovalState() {
        CalendarEventRequest calendarRequest =
                new CalendarEventRequest(
                        "Nova proje toplantısı",
                        ZonedDateTime.parse(
                                "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                        )
                );


        CalendarActionProposal proposal =
                CalendarActionProposal.propose(calendarRequest);

        assertNotNull(proposal.actionId());
        assertSame(
                calendarRequest,
                proposal.calendarEventRequest()
        );
    }

    @Test
    void shouldRejectProposalWithoutCalendarRequest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> CalendarActionProposal.propose(null)
        );

        assertEquals(
                "Calendar event request must not be null",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectPendingProposal(){
        CalendarEventRequest request =new CalendarEventRequest(
                "NOVA proje toplantısı",
                ZonedDateTime.parse(
                        "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                )
        );

        CalendarActionProposal proposal = CalendarActionProposal.propose(request);

        proposal.reject();

        assertEquals(
                CalendarActionStatus.REJECTED,
                proposal.status()
        );

    }

    @Test
    void shouldRejectRepeatedRejection(){
        CalendarEventRequest request =new CalendarEventRequest(
                "NOVA proje toplantısı",
                ZonedDateTime.parse(
                        "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                )
        );
        CalendarActionProposal proposal = CalendarActionProposal.propose(request);

        proposal.reject();

        InvalidCalendarActionStateException exception =
                assertThrows(
                        InvalidCalendarActionStateException.class,
                        proposal::reject
                );


        assertEquals(
                "Calendar action must be pending approval but was REJECTED",
                exception.getMessage()
                );

    }


    @Test
    void shouldMarkPendingProposalAsExecuted(){
        CalendarEventRequest calendarRequest =
                new CalendarEventRequest(
                        "NOVA proje toplantısı",
                        ZonedDateTime.parse(
                                "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                        )
                );

        CalendarActionProposal proposal =
                CalendarActionProposal.propose(calendarRequest);

        ScheduledCalendarEvent scheduledCalendarEvent = new ScheduledCalendarEvent(
                UUID.randomUUID(),
                calendarRequest.title(),
                calendarRequest.startsAt()
        );

        proposal.markExecuted(scheduledCalendarEvent);

        assertEquals(
                CalendarActionStatus.EXECUTED,
                proposal.status()
        );

        assertSame(
                scheduledCalendarEvent,
                proposal.scheduledEvent().orElseThrow()
        );
    }

    @Test
    void shouldRejectExecutionWithoutScheduledEvent(){
        CalendarEventRequest request =new CalendarEventRequest(
                "NOVA proje toplantısı",
                ZonedDateTime.parse(
                        "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                )
        );


        CalendarActionProposal proposal = CalendarActionProposal.propose(request);


        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> proposal.markExecuted(null)
                );


        assertEquals(
                "Scheduled calendar event must not be null",
                exception.getMessage()
        );

        assertEquals(
                CalendarActionStatus.PENDING_APPROVAL,
                proposal.status()
        );

        assertTrue(proposal.scheduledEvent().isEmpty());

    }
}
