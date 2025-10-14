package com.raroki.atsumarii.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Convert User entity to UserDto
     */
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .userid(user.getUserid())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .dateofbirth(user.getDateofbirth())
                .bio(user.getBio())
                .profileimgurl(user.getProfileimgurl())
                .createdat(user.getCreatedat())
                .updatedat(user.getUpdatedat())
                .build();
    }

    /**
     * Convert UserDto to User entity
     */
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .userid(dto.getUserid())
                .username(dto.getUsername())
                .fullname(dto.getFullname())
                .email(dto.getEmail())
                .dateofbirth(dto.getDateofbirth())
                .bio(dto.getBio())
                .profileimgurl(dto.getProfileimgurl())
                .createdat(dto.getCreatedat())
                .updatedat(dto.getUpdatedat())
                .build();
    }

    /**
     * Convert list of User entities to list of UserDtos
     */
    public List<UserDto> toDtoList(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of UserDtos to list of User entities
     */
    public List<User> toEntityList(List<UserDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Update existing User entity from UserDto
     */
    public void updateEntityFromDto(UserDto dto, User user) {
        if (dto == null || user == null) {
            return;
        }

        user.setUsername(dto.getUsername());
        user.setFullname(dto.getFullname());
        user.setEmail(dto.getEmail());
        user.setDateofbirth(dto.getDateofbirth());
        user.setBio(dto.getBio());
        user.setProfileimgurl(dto.getProfileimgurl());
        // Note: createdat and updatedat are usually managed by the database/JPA
    }
}