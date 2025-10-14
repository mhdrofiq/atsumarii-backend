package com.raroki.atsumarii.groupmembership;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.raroki.atsumarii.group.Group;
import com.raroki.atsumarii.user.User;

@Component
public class GroupMembershipMapper {

    /**
     * Convert GroupMembership entity to GroupMembershipDto
     */
    public GroupMembershipDto toDto(GroupMembership membership) {
        if (membership == null) {
            return null;
        }

        return GroupMembershipDto.builder()
                .membershipid(membership.getMembershipid())
                .joinedat(membership.getJoinedat())
                .updatedat(membership.getUpdatedat())
                .userid(membership.getUser() != null ? membership.getUser().getUserid() : null)
                .username(membership.getUser() != null ? membership.getUser().getUsername() : null)
                .userEmail(membership.getUser() != null ? membership.getUser().getEmail() : null)
                .groupid(membership.getGroup() != null ? membership.getGroup().getGroupid() : null)
                .groupName(membership.getGroup() != null ? membership.getGroup().getGroupname() : null)
                .build();
    }

    /**
     * Convert GroupMembershipDto to GroupMembership entity
     */
    public GroupMembership toEntity(GroupMembershipDto dto) {
        if (dto == null) {
            return null;
        }

        GroupMembership.GroupMembershipBuilder builder = GroupMembership.builder()
                .membershipid(dto.getMembershipid())
                .joinedat(dto.getJoinedat())
                .updatedat(dto.getUpdatedat());

        // Set user if userid is provided
        if (dto.getUserid() != null) {
            User user = User.builder()
                    .userid(dto.getUserid())
                    .username(dto.getUsername())
                    .email(dto.getUserEmail())
                    .build();
            builder.user(user);
        }

        // Set group if groupid is provided
        if (dto.getGroupid() != null) {
            Group group = Group.builder()
                    .groupid(dto.getGroupid())
                    .groupname(dto.getGroupName())
                    .build();
            builder.group(group);
        }

        return builder.build();
    }

    /**
     * Convert list of GroupMembership entities to list of GroupMembershipDtos
     */
    public List<GroupMembershipDto> toDtoList(List<GroupMembership> memberships) {
        if (memberships == null) {
            return null;
        }
        return memberships.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of GroupMembershipDtos to list of GroupMembership entities
     */
    public List<GroupMembership> toEntityList(List<GroupMembershipDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Update existing GroupMembership entity from GroupMembershipDto
     */
    public void updateEntityFromDto(GroupMembershipDto dto, GroupMembership membership) {
        if (dto == null || membership == null) {
            return;
        }

        // Note: Usually only joinedat and updatedat might be updated
        // User and Group relationships are typically not changed after creation
        // updatedat is usually managed by the database/JPA
    }
}