package com.valentin.lecture.controllers;

import com.valentin.lecture.entities.EventType;
import com.valentin.lecture.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @MockBean
    EventRepository repository;

    @Autowired
    MockMvc mvc;



    @Test
    void findAllEventTypesTest() throws Exception {

        List<EventType> allRecords = new ArrayList<>();
        EventType firstRecord = new EventType();
        EventType secondRecord = new EventType();
        firstRecord.setTypeId(1);
        secondRecord.setTypeId(2);

        allRecords.add(firstRecord);
        allRecords.add(secondRecord);

        String expected = "[{\"typeId\":1,\"eventType\":null},{\"typeId\":2,\"eventType\":null}]";

        BDDMockito
                .given(repository.findAll())
                .willReturn(allRecords);

        this.mvc.perform(MockMvcRequestBuilders
                .get("/api/events")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }


    @Test
    void deleteEventTypeTest() throws Exception {

        int typeId = 1;
        EventType existingRecord = new EventType();
        existingRecord.setTypeId(typeId);

        BDDMockito
                .given(repository.existsById(typeId))
                .willReturn(true);

        this.mvc.perform(MockMvcRequestBuilders
                .delete("/api/events/" + typeId))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }

}