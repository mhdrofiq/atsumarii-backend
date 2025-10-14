package com.raroki.atsumarii.event;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.raroki.atsumarii.group.Group;

@Component
public class EventMapper {

    /**
     * Convert Event entity to EventDto
     */
    public EventDto toDto(Event event) {
        if (event == null) {
            return null;
        }

        return EventDto.builder()
                .eventid(event.getEventid())
                .eventname(event.getEventname())
                .tags(event.getTags())
                .description(event.getDescription())
                .eventimgurl(event.getEventimgurl())
                .location(event.getLocation())
                .start(event.getStart())
                .end(event.getEnd())
                .createdat(event.getCreatedat())
                .updatedat(event.getUpdatedat())
                .groupid(event.getGroup() != null ? event.getGroup().getGroupid() : null)
                .groupName(event.getGroup() != null ? event.getGroup().getGroupname() : null)
                .build();
    }

    /**
     * Convert EventDto to Event entity
     */
    public Event toEntity(EventDto dto) {
        if (dto == null) {
            return null;
        }

        Event.EventBuilder builder = Event.builder()
                .eventid(dto.getEventid())
                .eventname(dto.getEventname())
                .tags(dto.getTags())
                .description(dto.getDescription())
                .eventimgurl(dto.getEventimgurl())
                .location(dto.getLocation())
                .start(dto.getStart())
                .end(dto.getEnd())
                .createdat(dto.getCreatedat())
                .updatedat(dto.getUpdatedat());

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
     * Convert list of Event entities to list of EventDtos
     */
    public List<EventDto> toDtoList(List<Event> events) {
        if (events == null) {
            return null;
        }
        return events.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of EventDtos to list of Event entities
     */
    public List<Event> toEntityList(List<EventDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Update existing Event entity from EventDto
     */
    public void updateEntityFromDto(EventDto dto, Event event) {
        if (dto == null || event == null) {
            return;
        }

        event.setEventname(dto.getEventname());
        event.setTags(dto.getTags());
        event.setDescription(dto.getDescription());
        event.setEventimgurl(dto.getEventimgurl());
        event.setLocation(dto.getLocation());
        event.setStart(dto.getStart());
        event.setEnd(dto.getEnd());
        // Note: createdat and updatedat are usually managed by the database/JPA
        // Group should not be updated through this method typically
    }
}