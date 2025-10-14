package com.raroki.atsumarii.group;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * Get all groups
     * GET /api/groups
     */
    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        try {
            List<GroupDto> groups = groupService.getAllGroups();
            return ResponseEntity.ok(groups);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get group by ID
     * GET /api/groups/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Integer id) {
        try {
            return groupService.getGroupById(id)
                    .map(group -> ResponseEntity.ok(group))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create a new group
     * POST /api/groups?creatorId={creatorId}
     */
    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto, @RequestParam Integer creatorId) {
        try {
            GroupDto createdGroup = groupService.createGroup(groupDto, creatorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update an existing group
     * PUT /api/groups/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        try {
            GroupDto updatedGroup = groupService.updateGroup(id, groupDto);
            return ResponseEntity.ok(updatedGroup);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Delete a group
     * DELETE /api/groups/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}