package com.valentin.lecture.controllers;

import com.valentin.lecture.entities.EventType;
import com.valentin.lecture.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    EventRepository repository;

    @Autowired
    public EventController (EventRepository repository){
        this.repository = repository;
    }


    @GetMapping
    public ResponseEntity<Iterable<EventType>> findAllEventTypes (){

        List<EventType> allRecords = new ArrayList<>();

        for (EventType each : repository.findAll()) {
            allRecords.add(each);
        }

        if(allRecords.size()!=0){
            return ResponseEntity.ok(allRecords);
        } else {
            return ResponseEntity.noContent().build();
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<EventType> findByTypeId (@PathVariable (value = "id") int typeId){

        try{
            EventType record = repository.findEventTypeByTypeId(typeId);
            return ResponseEntity.ok(record);
        } catch (Exception e){
            // log the error
            return ResponseEntity.notFound().build();
        }
    }



}
