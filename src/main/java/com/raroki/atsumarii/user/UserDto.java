package com.raroki.atsumarii.user;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raroki.atsumarii.eventattendance.EventAttendanceDto;
import com.raroki.atsumarii.group.GroupDto;
import com.raroki.atsumarii.groupmembership.GroupMembershipDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    
    private Integer userid;
    
    private String username;
    
    private String fullname;
    
    private String email;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateofbirth;
    
    private String bio;
    
    private String profileimgurl;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdat;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedat;
    
    // List of group memberships for this user
    private List<GroupMembershipDto> groupMemberships;
    
    // List of event attendances for this user
    private List<EventAttendanceDto> eventAttendances;
    
    // List of groups created by this user
    private List<GroupDto> createdGroups;
}