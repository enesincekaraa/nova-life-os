package com.enesincekara.nova.calendar;

import com.enesincekara.nova.calendar.application.ScheduleCalendarEventService;
import com.enesincekara.nova.calendar.application.tool.CalendarTool;
import com.enesincekara.nova.calendar.infrastructure.memory.InMemoryCalendarTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CalendarSpringWiringTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldRegisterCalendarComponentsAsSpringBeans() {
        CalendarTool calendarTool =
                applicationContext.getBean(CalendarTool.class);

        ScheduleCalendarEventService service =
                applicationContext.getBean(
                        ScheduleCalendarEventService.class
                );

        assertInstanceOf(InMemoryCalendarTool.class, calendarTool);
        assertNotNull(service);
    }
}