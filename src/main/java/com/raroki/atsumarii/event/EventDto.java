package com.raroki.atsumarii.event;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raroki.atsumarii.eventattendance.EventAttendanceDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    
    private Integer eventid;
    
    private String eventname;
    
    private String tags;
    
    private String description;
    
    private String eventimgurl;
    
    private String location;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdat;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedat;
    
    // Group reference (simplified)
    private Integer groupid;
    
    // Group name for display purposes
    private String groupName;
    
    // List of participants for this event
    private List<EventAttendanceDto> participants;
}