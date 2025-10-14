package com.raroki.atsumarii.eventattendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAttendanceRepository extends JpaRepository<EventAttendance, Integer> {
    
    // Check if user is registered for event (used for validation in createAttendance)
    boolean existsByUserUseridAndEventEventid(Integer userid, Integer eventid);
}