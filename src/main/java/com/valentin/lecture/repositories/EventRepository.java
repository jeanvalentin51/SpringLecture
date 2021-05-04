package com.valentin.lecture.repositories;

import com.valentin.lecture.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventType,Integer> {

    EventType findEventTypeByTypeId (int typeId);
    List<EventType> findAll ();

/*
Tip: retrieve records by custom query
Names MUST match (afterdate and beforedate)

    @Query("FROM Event WHERE startDate >= : afterdate AND endDate <= : beforedate")
    List<Event> findEventByStartDateAfterAndEndDateBefore (@Param("afterdate") Date startdate, @Param("beforedate") Date enddate);

*/
}
