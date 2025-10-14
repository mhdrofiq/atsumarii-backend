package com.raroki.atsumarii.eventattendance;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.raroki.atsumarii.event.Event;
import com.raroki.atsumarii.user.User;

@Component
public class EventAttendanceMapper {

    /**
     * Convert EventAttendance entity to EventAttendanceDto
     */
    public EventAttendanceDto toDto(EventAttendance attendance) {
        if (attendance == null) {
            return null;
        }

        return EventAttendanceDto.builder()
                .attendanceid(attendance.getAttendanceid())
                .attendanceStatus(attendance.getAttendanceStatus())
                .registeredat(attendance.getRegisteredat())
                .updatedat(attendance.getUpdatedat())
                .eventid(attendance.getEvent() != null ? attendance.getEvent().getEventid() : null)
                .eventName(attendance.getEvent() != null ? attendance.getEvent().getEventname() : null)
                .eventStart(attendance.getEvent() != null ? attendance.getEvent().getStart() : null)
                .eventEnd(attendance.getEvent() != null ? attendance.getEvent().getEnd() : null)
                .userid(attendance.getUser() != null ? attendance.getUser().getUserid() : null)
                .username(attendance.getUser() != null ? attendance.getUser().getUsername() : null)
                .userEmail(attendance.getUser() != null ? attendance.getUser().getEmail() : null)
                .build();
    }

    /**
     * Convert EventAttendanceDto to EventAttendance entity
     */
    public EventAttendance toEntity(EventAttendanceDto dto) {
        if (dto == null) {
            return null;
        }

        EventAttendance.EventAttendanceBuilder builder = EventAttendance.builder()
                .attendanceid(dto.getAttendanceid())
                .attendanceStatus(dto.getAttendanceStatus())
                .registeredat(dto.getRegisteredat())
                .updatedat(dto.getUpdatedat());

        // Set event if eventid is provided
        if (dto.getEventid() != null) {
            Event event = Event.builder()
                    .eventid(dto.getEventid())
                    .eventname(dto.getEventName())
                    .start(dto.getEventStart())
                    .end(dto.getEventEnd())
                    .build();
            builder.event(event);
        }

        // Set user if userid is provided
        if (dto.getUserid() != null) {
            User user = User.builder()
                    .userid(dto.getUserid())
                    .username(dto.getUsername())
                    .email(dto.getUserEmail())
                    .build();
            builder.user(user);
        }

        return builder.build();
    }

    /**
     * Convert list of EventAttendance entities to list of EventAttendanceDtos
     */
    public List<EventAttendanceDto> toDtoList(List<EventAttendance> attendances) {
        if (attendances == null) {
            return null;
        }
        return attendances.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of EventAttendanceDtos to list of EventAttendance entities
     */
    public List<EventAttendance> toEntityList(List<EventAttendanceDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Update existing EventAttendance entity from EventAttendanceDto
     */
    public void updateEntityFromDto(EventAttendanceDto dto, EventAttendance attendance) {
        if (dto == null || attendance == null) {
            return;
        }

        // Usually only the attendance status can be updated
        attendance.setAttendanceStatus(dto.getAttendanceStatus());
        // Note: registeredat and updatedat are usually managed by the database/JPA
        // Event and User relationships are typically not changed after creation
    }


}