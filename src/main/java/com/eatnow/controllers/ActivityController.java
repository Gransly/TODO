package com.eatnow.controllers;

import com.eatnow.models.Activity;
import com.eatnow.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/random")
    @PreAuthorize("hasRole('USER')")
    public Activity getActivityRandom() {
        return activityService.addByRandom();
    }

    @GetMapping("/type")
    @PreAuthorize("hasRole('USER')")
    public Activity getActivityType(@RequestParam String type) {
        return activityService.addByType(type);
    }

    @GetMapping("/participants")
    @PreAuthorize("hasRole('USER')")
    public Activity getActivityParticipants(@RequestParam String count) {
        return activityService.addByParticipants(count);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public boolean deleteActivity(@PathVariable Long id) {
        return activityService.remove(id);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Activity> get(@PathVariable Long id) {
        return activityService.get(id);
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Activity> getAll() {
        return activityService.getAll();
    }

    @GetMapping("/get/user")
    @PreAuthorize("hasRole('USER')")
    public List<Activity> getAllByUser() {
        return activityService.getAllByUser();
    }

}
