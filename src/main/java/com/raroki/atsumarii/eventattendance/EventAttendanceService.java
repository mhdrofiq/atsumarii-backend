package com.raroki.atsumarii.eventattendance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raroki.atsumarii.event.Event;
import com.raroki.atsumarii.event.EventRepository;
import com.raroki.atsumarii.user.User;
import com.raroki.atsumarii.user.UserRepository;

@Service
@Transactional
public class EventAttendanceService {

    @Autowired
    private EventAttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventAttendanceMapper attendanceMapper;

    // Constants for attendance status
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_DECLINED = "DECLINED";

    /**
     * Get all attendances
     */
    @Transactional(readOnly = true)
    public List<EventAttendanceDto> getAllAttendances() {
        List<EventAttendance> attendances = attendanceRepository.findAll();
        return attendanceMapper.toDtoList(attendances);
    }

    /**
     * Get attendance by ID
     */
    @Transactional(readOnly = true)
    public Optional<EventAttendanceDto> getAttendanceById(Integer attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .map(attendanceMapper::toDto);
    }

    /**
     * Create new attendance
     */
    public EventAttendanceDto createAttendance(EventAttendanceDto attendanceDto) {
        // Validate required fields
        if (attendanceDto.getUserid() == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (attendanceDto.getEventid() == null) {
            throw new IllegalArgumentException("Event ID is required");
        }

        String status = attendanceDto.getAttendanceStatus();
        if (status == null || status.trim().isEmpty()) {
            status = STATUS_PENDING; // Default status
        }

        // Validate status
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Valid statuses: " + 
                STATUS_CONFIRMED + ", " + STATUS_PENDING + ", " + STATUS_DECLINED);
        }

        // Check if user exists
        User user = userRepository.findById(attendanceDto.getUserid())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + attendanceDto.getUserid()));

        // Check if event exists
        Event event = eventRepository.findById(attendanceDto.getEventid())
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + attendanceDto.getEventid()));

        // Check if registration already exists
        if (attendanceRepository.existsByUserUseridAndEventEventid(attendanceDto.getUserid(), attendanceDto.getEventid())) {
            throw new IllegalArgumentException("User is already registered for this event");
        }

        // Check if event is in the past
        if (event.getStart().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot register for past events");
        }

        // Set creation timestamp and ensure auto-generation of ID
        attendanceDto.setRegisteredat(LocalDateTime.now());
        attendanceDto.setAttendanceStatus(status);
        attendanceDto.setAttendanceid(null); // Ensure auto-generation

        EventAttendance attendance = attendanceMapper.toEntity(attendanceDto);
        attendance.setUser(user);
        attendance.setEvent(event);
        
        EventAttendance savedAttendance = attendanceRepository.save(attendance);
        return attendanceMapper.toDto(savedAttendance);
    }

    /**
     * Update attendance
     */
    public EventAttendanceDto updateAttendance(Integer attendanceId, EventAttendanceDto attendanceDto) {
        EventAttendance existingAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found with ID: " + attendanceId));

        // Validate status if provided
        if (attendanceDto.getAttendanceStatus() != null && !isValidStatus(attendanceDto.getAttendanceStatus())) {
            throw new IllegalArgumentException("Invalid status: " + attendanceDto.getAttendanceStatus() + ". Valid statuses: " + 
                STATUS_CONFIRMED + ", " + STATUS_PENDING + ", " + STATUS_DECLINED);
        }

        // Update fields
        attendanceMapper.updateEntityFromDto(attendanceDto, existingAttendance);
        existingAttendance.setUpdatedat(LocalDateTime.now());

        EventAttendance savedAttendance = attendanceRepository.save(existingAttendance);
        return attendanceMapper.toDto(savedAttendance);
    }

    /**
     * Delete attendance
     */
    public void deleteAttendance(Integer attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new IllegalArgumentException("Attendance not found with ID: " + attendanceId);
        }
        attendanceRepository.deleteById(attendanceId);
    }

    /**
     * Validate attendance status
     */
    private boolean isValidStatus(String status) {
        return STATUS_CONFIRMED.equals(status) || STATUS_PENDING.equals(status) || STATUS_DECLINED.equals(status);
    }
}