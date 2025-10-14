package com.raroki.atsumarii.eventattendance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendances")
public class EventAttendanceController {

    @Autowired
    private EventAttendanceService attendanceService;

    /**
     * Get all attendances
     * GET /api/attendances
     */
    @GetMapping
    public ResponseEntity<List<EventAttendanceDto>> getAllAttendances() {
        try {
            List<EventAttendanceDto> attendances = attendanceService.getAllAttendances();
            return ResponseEntity.ok(attendances);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get attendance by ID
     * GET /api/attendances/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventAttendanceDto> getAttendanceById(@PathVariable Integer id) {
        try {
            return attendanceService.getAttendanceById(id)
                    .map(attendance -> ResponseEntity.ok(attendance))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create new attendance (register for event)
     * POST /api/attendances
     */
    @PostMapping
    public ResponseEntity<EventAttendanceDto> createAttendance(@RequestBody EventAttendanceDto attendanceDto) {
        try {
            EventAttendanceDto createdAttendance = attendanceService.createAttendance(attendanceDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update attendance (change status)
     * PUT /api/attendances/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventAttendanceDto> updateAttendance(@PathVariable Integer id, @RequestBody EventAttendanceDto attendanceDto) {
        try {
            EventAttendanceDto updatedAttendance = attendanceService.updateAttendance(id, attendanceDto);
            return ResponseEntity.ok(updatedAttendance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Delete attendance (cancel registration)
     * DELETE /api/attendances/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Integer id) {
        try {
            attendanceService.deleteAttendance(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}