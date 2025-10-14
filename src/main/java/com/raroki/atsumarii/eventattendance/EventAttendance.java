package com.raroki.atsumarii.eventattendance;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.raroki.atsumarii.event.Event;
import com.raroki.atsumarii.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "t_event_attendance")
public class EventAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceid;
    
    @Column(name = "attendance_status")
    private String attendanceStatus;
    
    @Column(updatable = false, nullable = false)
    private LocalDateTime registeredat;
    @Column(insertable = false)
    private LocalDateTime updatedat;

    // Many participants can attend one event
    @ManyToOne
    @JoinColumn(name = "eventid")
    @JsonBackReference("event-participants")
    private Event event;

    // Many participants can belong to one user
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonBackReference("user-eventAttendances")
    private User user;
}
