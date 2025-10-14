package com.raroki.atsumarii.groupmembership;

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
public class GroupMembershipDto {
    
    private Integer membershipid;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinedat;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedat;
    
    // User reference (simplified)
    private Integer userid;
    
    // User information for display
    private String username;
    
    private String userEmail;
    
    // Group reference (simplified)
    private Integer groupid;
    
    // Group information for display
    private String groupName;
}