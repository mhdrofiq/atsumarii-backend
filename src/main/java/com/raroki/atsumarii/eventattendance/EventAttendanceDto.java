package com.raroki.atsumarii.eventattendance;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventAttendanceDto {
    
    private Integer attendanceid;
    
    private String attendanceStatus;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredat;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedat;
    
    // Event reference (simplified)
    private Integer eventid;
    
    // Event information for display
    private String eventName;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventStart;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventEnd;
    
    // User reference (simplified)
    private Integer userid;
    
    // User information for display
    private String username;
    
    private String userEmail;
}