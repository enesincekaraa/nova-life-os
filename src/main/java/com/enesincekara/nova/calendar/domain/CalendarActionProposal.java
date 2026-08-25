package com.enesincekara.nova.calendar.domain;

import java.util.Optional;
import java.util.UUID;

public final class CalendarActionProposal {
    private final UUID actionId;
    private final CalendarEventRequest calendarEventRequest;
    private CalendarActionStatus status;
    private ScheduledCalendarEvent scheduledEvent;


    private CalendarActionProposal(
            UUID actionId,
            CalendarEventRequest calendarEventRequest
    ){

        if (calendarEventRequest == null) {
            throw new IllegalArgumentException(
                    "Calendar event request must not be null"
            );
        }
        this.actionId = actionId;
        this.calendarEventRequest = calendarEventRequest;
        this.status=CalendarActionStatus.PENDING_APPROVAL;
    }


    public static CalendarActionProposal propose(
            CalendarEventRequest calendarEventRequest
    ){
        return new CalendarActionProposal(
                UUID.randomUUID(),
                calendarEventRequest
        );
    }


    public void reject() {
        requirePendingApproval();
        this.status = CalendarActionStatus.REJECTED;
    }
    public void markExecuted(ScheduledCalendarEvent scheduledEvent) {
        requirePendingApproval();

        if (scheduledEvent == null) {
            throw new IllegalArgumentException(
                    "Scheduled calendar event must not be null"
            );
        }

        this.scheduledEvent=scheduledEvent;
        this.status = CalendarActionStatus.EXECUTED;
    }

    private void requirePendingApproval() {
        if (this.status != CalendarActionStatus.PENDING_APPROVAL) {
            throw  new InvalidCalendarActionStateException(status);
        }
    }





    public UUID actionId() {
        return actionId;
    }

    public CalendarEventRequest calendarEventRequest() {
        return calendarEventRequest;
    }

    public CalendarActionStatus status() {
        return status;
    }
    public Optional<ScheduledCalendarEvent> scheduledEvent() {
        return Optional.ofNullable(scheduledEvent);
    }


}
