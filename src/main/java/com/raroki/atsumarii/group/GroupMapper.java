package com.raroki.atsumarii.group;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.raroki.atsumarii.user.User;

@Component
public class GroupMapper {

    /**
     * Convert Group entity to GroupDto
     */
    public GroupDto toDto(Group group) {
        if (group == null) {
            return null;
        }

        return GroupDto.builder()
                .groupid(group.getGroupid())
                .groupname(group.getGroupname())
                .description(group.getDescription())
                .groupimgurl(group.getGroupimgurl())
                .createdat(group.getCreatedat())
                .updatedat(group.getUpdatedat())
                .creatorid(group.getCreator() != null ? group.getCreator().getUserid() : null)
                .creatorUsername(group.getCreator() != null ? group.getCreator().getUsername() : null)
                .build();
    }

    /**
     * Convert GroupDto to Group entity
     */
    public Group toEntity(GroupDto dto) {
        if (dto == null) {
            return null;
        }

        Group.GroupBuilder builder = Group.builder()
                .groupid(dto.getGroupid())
                .groupname(dto.getGroupname())
                .description(dto.getDescription())
                .groupimgurl(dto.getGroupimgurl())
                .createdat(dto.getCreatedat())
                .updatedat(dto.getUpdatedat());

        // Set creator if creatorid is provided
        if (dto.getCreatorid() != null) {
            User creator = User.builder()
                    .userid(dto.getCreatorid())
                    .username(dto.getCreatorUsername())
                    .build();
            builder.creator(creator);
        }

        return builder.build();
    }

    /**
     * Convert list of Group entities to list of GroupDtos
     */
    public List<GroupDto> toDtoList(List<Group> groups) {
        if (groups == null) {
            return null;
        }
        return groups.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of GroupDtos to list of Group entities
     */
    public List<Group> toEntityList(List<GroupDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Update existing Group entity from GroupDto
     */
    public void updateEntityFromDto(GroupDto dto, Group group) {
        if (dto == null || group == null) {
            return;
        }

        group.setGroupname(dto.getGroupname());
        group.setDescription(dto.getDescription());
        group.setGroupimgurl(dto.getGroupimgurl());
        // Note: createdat and updatedat are usually managed by the database/JPA
        // Creator should not be updated through this method typically
    }
}