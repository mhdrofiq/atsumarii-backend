package com.raroki.atsumarii.groupmembership;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Integer> {
    
    // Check if user is member of group (for validation)
    boolean existsByUserUseridAndGroupGroupid(Integer userid, Integer groupid);
}