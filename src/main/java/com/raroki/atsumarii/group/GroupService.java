package com.raroki.atsumarii.group;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raroki.atsumarii.user.User;
import com.raroki.atsumarii.user.UserRepository;

@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupMapper groupMapper;

    /**
     * Get all groups
     */
    @Transactional(readOnly = true)
    public List<GroupDto> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.toDtoList(groups);
    }

    /**
     * Get group by ID
     */
    @Transactional(readOnly = true)
    public Optional<GroupDto> getGroupById(Integer groupId) {
        return groupRepository.findById(groupId)
                .map(groupMapper::toDto);
    }

    /**
     * Create a new group
     */
    public GroupDto createGroup(GroupDto groupDto, Integer creatorId) {
        // Validate required fields
        if (groupDto.getGroupname() == null || groupDto.getGroupname().trim().isEmpty()) {
            throw new IllegalArgumentException("Group name is required");
        }

        if (creatorId == null) {
            throw new IllegalArgumentException("Creator ID is required");
        }

        // Check if group name already exists
        if (groupRepository.existsByGroupname(groupDto.getGroupname())) {
            throw new IllegalArgumentException("Group name already exists: " + groupDto.getGroupname());
        }

        // Verify creator exists
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("Creator not found with ID: " + creatorId));

        // Set creation timestamp and creator
        groupDto.setCreatedat(LocalDateTime.now());
        groupDto.setCreatorid(creatorId);
        groupDto.setCreatorUsername(creator.getUsername());
        groupDto.setGroupid(null); // Ensure auto-generation

        Group group = groupMapper.toEntity(groupDto);
        group.setCreator(creator);
        
        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDto(savedGroup);
    }

    /**
     * Update an existing group
     */
    public GroupDto updateGroup(Integer groupId, GroupDto groupDto) {
        Group existingGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with ID: " + groupId));

        // Validate group name uniqueness (if changed)
        if (!existingGroup.getGroupname().equals(groupDto.getGroupname()) && 
            groupRepository.existsByGroupname(groupDto.getGroupname())) {
            throw new IllegalArgumentException("Group name already exists: " + groupDto.getGroupname());
        }

        // Update fields (excluding creator)
        groupMapper.updateEntityFromDto(groupDto, existingGroup);
        existingGroup.setUpdatedat(LocalDateTime.now());

        Group savedGroup = groupRepository.save(existingGroup);
        return groupMapper.toDto(savedGroup);
    }

    /**
     * Delete a group
     */
    public void deleteGroup(Integer groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("Group not found with ID: " + groupId);
        }
        groupRepository.deleteById(groupId);
    }
}