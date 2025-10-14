package com.raroki.atsumarii.event;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.raroki.atsumarii.eventattendance.EventAttendance;
import com.raroki.atsumarii.group.Group;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "t_events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventid;
    private String eventname;
    private String tags;
    private String description;
    private String eventimgurl;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdat;
    @Column(insertable = false)
    private LocalDateTime updatedat;
    
    // Many events can belong to one group
    // Owning side of the relationship
    @ManyToOne
    @JoinColumn(name = "groupid")
    @JsonBackReference("group-events")
    private Group group;
    
    // One event can have many participants
    // mappedBy should match the field name in EventAttendance class
    @OneToMany(mappedBy = "event")
    @JsonManagedReference("event-participants")
    private List<EventAttendance> participants;
}
