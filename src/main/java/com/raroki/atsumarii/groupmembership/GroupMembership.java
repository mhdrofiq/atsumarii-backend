package com.raroki.atsumarii.groupmembership;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.raroki.atsumarii.group.Group;
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
@Table(name = "t_group_membership")
public class GroupMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membershipid;
    @Column(updatable = false, nullable = false)
    private LocalDateTime joinedat;
    @Column(insertable = false)
    private LocalDateTime updatedat;

    // Many members can belong to one user
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonBackReference("user-groupMemberships")
    private User user;

    // Many members can belong to one group
    @ManyToOne
    @JoinColumn(name = "groupid")
    @JsonBackReference("group-members")
    private Group group;
}
