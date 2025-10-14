package com.raroki.atsumarii.group;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raroki.atsumarii.event.EventDto;
import com.raroki.atsumarii.groupmembership.GroupMembershipDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDto {
    
    private Integer groupid;
    
    private String groupname;
    
    private String description;
    
    private String groupimgurl;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdat;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedat;
    
    // Creator user ID (simplified reference)
    private Integer creatorid;
    
    // Creator username for display purposes
    private String creatorUsername;
    
    // List of events in this group
    private List<EventDto> events;
    
    // List of members in this group
    private List<GroupMembershipDto> members;
}