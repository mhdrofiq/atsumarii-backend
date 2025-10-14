package com.raroki.atsumarii.groupmembership;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raroki.atsumarii.group.Group;
import com.raroki.atsumarii.group.GroupRepository;
import com.raroki.atsumarii.user.User;
import com.raroki.atsumarii.user.UserRepository;

@Service
@Transactional
public class GroupMembershipService {

    @Autowired
    private GroupMembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMembershipMapper membershipMapper;

    /**
     * Get all memberships
     */
    @Transactional(readOnly = true)
    public List<GroupMembershipDto> getAllMemberships() {
        List<GroupMembership> memberships = membershipRepository.findAll();
        return membershipMapper.toDtoList(memberships);
    }

    /**
     * Get membership by ID
     */
    @Transactional(readOnly = true)
    public Optional<GroupMembershipDto> getMembershipById(Integer membershipId) {
        return membershipRepository.findById(membershipId)
                .map(membershipMapper::toDto);
    }

    /**
     * Create new membership
     */
    public GroupMembershipDto createMembership(GroupMembershipDto membershipDto) {
        // Validate input
        if (membershipDto.getUserid() == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (membershipDto.getGroupid() == null) {
            throw new IllegalArgumentException("Group ID is required");
        }

        // Check if user exists
        User user = userRepository.findById(membershipDto.getUserid())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + membershipDto.getUserid()));

        // Check if group exists
        Group group = groupRepository.findById(membershipDto.getGroupid())
                .orElseThrow(() -> new IllegalArgumentException("Group not found with ID: " + membershipDto.getGroupid()));

        // Check if membership already exists
        if (membershipRepository.existsByUserUseridAndGroupGroupid(membershipDto.getUserid(), membershipDto.getGroupid())) {
            throw new IllegalArgumentException("User is already a member of this group");
        }

        // Create new membership
        GroupMembership membership = GroupMembership.builder()
                .user(user)
                .group(group)
                .joinedat(LocalDateTime.now())
                .build();

        GroupMembership savedMembership = membershipRepository.save(membership);
        return membershipMapper.toDto(savedMembership);
    }

    /**
     * Update membership
     */
    public GroupMembershipDto updateMembership(Integer membershipId, GroupMembershipDto membershipDto) {
        GroupMembership existingMembership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with ID: " + membershipId));

        // Update the entity from DTO
        membershipMapper.updateEntityFromDto(membershipDto, existingMembership);
        existingMembership.setUpdatedat(LocalDateTime.now());

        GroupMembership updatedMembership = membershipRepository.save(existingMembership);
        return membershipMapper.toDto(updatedMembership);
    }

    /**
     * Delete membership
     */
    public void deleteMembership(Integer membershipId) {
        if (!membershipRepository.existsById(membershipId)) {
            throw new IllegalArgumentException("Membership not found with ID: " + membershipId);
        }
        membershipRepository.deleteById(membershipId);
    }
}