package com.raroki.atsumarii.group;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.raroki.atsumarii.event.Event;
import com.raroki.atsumarii.groupmembership.GroupMembership;
import com.raroki.atsumarii.user.User;

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
@Table(name = "t_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupid;
    @Column(unique = true)
    private String groupname;
    private String description;
    private String groupimgurl;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdat;
    @Column(insertable = false)
    private LocalDateTime updatedat;

    // One group can have many events
    // mappedBy should match the field name in Event class
    @OneToMany(mappedBy = "group")
    @JsonManagedReference("group-events") 
    private List<Event> events;

    // One group can have many members
    // mappedBy should match the field name in GroupMembership class
    @OneToMany(mappedBy = "group")
    @JsonManagedReference("group-members") 
    private List<GroupMembership> members;

    // Many groups can be created by one user
    // This is the inverse side of the relationship
    @ManyToOne
    @JoinColumn(name = "creatorid")
    @JsonBackReference("user-createdGroups")
    private User creator;
}