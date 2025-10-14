package com.raroki.atsumarii.event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raroki.atsumarii.group.Group;
import com.raroki.atsumarii.group.GroupRepository;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EventMapper eventMapper;

    /**
     * Get all events
     */
    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.toDtoList(events);
    }

    /**
     * Get event by ID
     */
    @Transactional(readOnly = true)
    public Optional<EventDto> getEventById(Integer eventId) {
        return eventRepository.findById(eventId)
                .map(eventMapper::toDto);
    }

    /**
     * Create a new event
     */
    public EventDto createEvent(EventDto eventDto, Integer groupId) {
        // Validate required fields
        if (eventDto.getEventname() == null || eventDto.getEventname().trim().isEmpty()) {
            throw new IllegalArgumentException("Event name is required");
        }

        if (eventDto.getStart() == null) {
            throw new IllegalArgumentException("Event start time is required");
        }

        if (eventDto.getEnd() == null) {
            throw new IllegalArgumentException("Event end time is required");
        }

        if (groupId == null) {
            throw new IllegalArgumentException("Group ID is required");
        }

        // Validate event times
        if (eventDto.getEnd().isBefore(eventDto.getStart()) || eventDto.getEnd().equals(eventDto.getStart())) {
            throw new IllegalArgumentException("Event end time must be after start time");
        }

        // Verify group exists
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with ID: " + groupId));

        // Set creation timestamp and group
        eventDto.setCreatedat(LocalDateTime.now());
        eventDto.setGroupid(groupId);
        eventDto.setGroupName(group.getGroupname());
        eventDto.setEventid(null); // Ensure auto-generation

        Event event = eventMapper.toEntity(eventDto);
        event.setGroup(group);
        
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    /**
     * Update an existing event
     */
    public EventDto updateEvent(Integer eventId, EventDto eventDto) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));

        // Validate event times if they are being updated
        if (eventDto.getStart() != null && eventDto.getEnd() != null) {
            if (eventDto.getEnd().isBefore(eventDto.getStart()) || eventDto.getEnd().equals(eventDto.getStart())) {
                throw new IllegalArgumentException("Event end time must be after start time");
            }
        }

        // Update fields (excluding group)
        eventMapper.updateEntityFromDto(eventDto, existingEvent);
        existingEvent.setUpdatedat(LocalDateTime.now());

        Event savedEvent = eventRepository.save(existingEvent);
        return eventMapper.toDto(savedEvent);
    }

    /**
     * Delete an event
     */
    public void deleteEvent(Integer eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new IllegalArgumentException("Event not found with ID: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }
}