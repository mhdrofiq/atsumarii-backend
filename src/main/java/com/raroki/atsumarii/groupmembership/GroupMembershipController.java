package com.raroki.atsumarii.groupmembership;

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
@RequestMapping("/api/memberships")
public class GroupMembershipController {

    @Autowired
    private GroupMembershipService membershipService;

    /**
     * Get all memberships
     * GET /api/memberships
     */
    @GetMapping
    public ResponseEntity<List<GroupMembershipDto>> getAllMemberships() {
        try {
            List<GroupMembershipDto> memberships = membershipService.getAllMemberships();
            return ResponseEntity.ok(memberships);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get membership by ID
     * GET /api/memberships/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupMembershipDto> getMembershipById(@PathVariable Integer id) {
        try {
            return membershipService.getMembershipById(id)
                    .map(membership -> ResponseEntity.ok(membership))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Create new membership
     * POST /api/memberships
     */
    @PostMapping
    public ResponseEntity<GroupMembershipDto> createMembership(@RequestBody GroupMembershipDto membershipDto) {
        try {
            GroupMembershipDto createdMembership = membershipService.createMembership(membershipDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMembership);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update membership
     * PUT /api/memberships/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<GroupMembershipDto> updateMembership(@PathVariable Integer id, @RequestBody GroupMembershipDto membershipDto) {
        try {
            GroupMembershipDto updatedMembership = membershipService.updateMembership(id, membershipDto);
            return ResponseEntity.ok(updatedMembership);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Delete membership
     * DELETE /api/memberships/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Integer id) {
        try {
            membershipService.deleteMembership(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}