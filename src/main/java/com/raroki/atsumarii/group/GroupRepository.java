package com.raroki.atsumarii.group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    
    // Check if group name exists (used for validation in createGroup and updateGroup)
    boolean existsByGroupname(String groupname);
}