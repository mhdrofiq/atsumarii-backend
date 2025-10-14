package com.raroki.atsumarii.user;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.raroki.atsumarii.eventattendance.EventAttendance;
import com.raroki.atsumarii.group.Group;
import com.raroki.atsumarii.groupmembership.GroupMembership;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String username;
    private String fullname;
    @Column(unique = true)
    private String email;
    private LocalDateTime dateofbirth;
    private String bio;
    private String profileimgurl;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdat;
    @Column(insertable = false)
    private LocalDateTime updatedat;

    // One user can have many group memberships
    // mappedBy should match the field name in GroupMembership class
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-groupMemberships")
    private List<GroupMembership> groupMemberships;

    // One user can have many event attendances
    // mappedBy should match the field name in EventAttendance class
    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-eventAttendances")
    private List<EventAttendance> eventAttendances;

    // One user can create many groups
    // mappedBy should match the field name in Group class
    @OneToMany(mappedBy = "creator")
    @JsonManagedReference("user-createdGroups")
    private List<Group> createdGroups;
}
