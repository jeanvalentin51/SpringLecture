package com.valentin.lecture.entities;

import javax.persistence.*;

@Entity
@Table(name = "event_type")
public class EventType {

    @Id
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;

/*

Event types:

    0 - Milonga
    1 - Workshop
    2 - Festival
    3 - Class
*/


}
