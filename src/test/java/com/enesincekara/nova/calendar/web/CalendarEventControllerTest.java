package com.enesincekara.nova.calendar.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalendarEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldScheduleCalendarEvent() throws Exception {
        mockMvc.perform(
                        post("/api/v1/calendar/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "title": "Proje toplantısı",
                                          "startsAt": "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                                        }
                                        """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventId").isNotEmpty())
                .andExpect(jsonPath("$.title")
                        .value("Proje toplantısı"))
                .andExpect(jsonPath("$.startsAt").isNotEmpty());
    }

    @Test
    void shouldRejectBlankTitle() throws Exception {
        mockMvc.perform(
                        post("/api/v1/calendar/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    {
                                      "title": " ",
                                      "startsAt": "2026-08-30T10:00:00+03:00[Europe/Istanbul]"
                                    }
                                    """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRejectMissingStartTime() throws Exception {
        mockMvc.perform(
                        post("/api/v1/calendar/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    {
                                      "title": "Proje toplantısı"
                                    }
                                    """)
                )
                .andExpect(status().isBadRequest());
    }
}