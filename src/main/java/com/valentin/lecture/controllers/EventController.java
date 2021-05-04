package com.valentin.lecture.controllers;

import com.valentin.lecture.entities.EventType;
import com.valentin.lecture.repositories.EventRepository;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    @ApiOperation(value = "Retrieve all records")
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
    @ApiOperation(value = "Get specific event type based on id")
    public ResponseEntity<EventType> findByTypeId (@PathVariable (value = "id") int typeId){

        try{
            EventType record = repository.findEventTypeByTypeId(typeId);
            return ResponseEntity.ok(record);
        } catch (Exception e){
            // log the error
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Delete event type by id")
    public void deleteEventType (@PathVariable (value = "id") int typeId){

        try {
            repository.deleteById(typeId);
        } catch (Exception e) {
            // log error
        }
    }


    @PostMapping
    @ApiOperation(value = "Create new event type")
    public EventType addNewEventType (@Validated @RequestBody EventType newEventType){

        try{
            return repository.save(newEventType);
        } catch (Exception e) {
            // log error
            return null;
        }
    }


    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Update event type by id")
    public ResponseEntity<EventType> updateEvent (@PathVariable (value = "id") int typeId, @Validated @RequestBody EventType newEventType){

        EventType eventToUpdate = repository.findEventTypeByTypeId(typeId);
        EventType updatedRecord = null;

        try {
            eventToUpdate.setEventType(newEventType.getEventType());
            eventToUpdate.setTypeId(newEventType.getTypeId());

            updatedRecord = repository.save(eventToUpdate);

            return ResponseEntity.ok(updatedRecord);
        } catch (Exception e) {
            // log error
            return ResponseEntity.noContent().build();
        }
    }

}
