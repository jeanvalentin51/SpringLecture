package com.valentin.lecture.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "event_type")
public class EventType {

    @Id
    @Column(name = "type_id")
    @ApiModelProperty (value = "This is the primary key")
    private int typeId;

    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;


    // --------- Getters and Setters --------------

    public int getTypeId() {return typeId; }

    public void setTypeId(int typeId) {this.typeId = typeId; }

    public String getEventType() {return eventType;}

    public void setEventType(String eventType) {this.eventType = eventType;}


    /*
Tip: when column name is a reserved name

    @Column (name = "`rows`")
    private String rows;

Tip: when the pk is autogenerated

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

---------------------------------

Event types values:

    0 - Milonga
    1 - Workshop
    2 - Festival
    3 - Class
*/


}
