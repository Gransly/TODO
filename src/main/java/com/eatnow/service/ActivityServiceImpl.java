package com.eatnow.service;

import com.eatnow.feign.ActivityFeign;
import com.eatnow.models.Activity;
import com.eatnow.models.ActivityStatus;
import com.eatnow.models.User;
import com.eatnow.repository.ActivityRepository;
import com.eatnow.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityFeign feign;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Override
    public Activity addByRandom() {
        User user = getUser();

        JsonNode activityJson = feign.getRandomActivity();
        Activity activity = getActivity(activityJson);


        List<Activity> activities = user.getActivities();
        activities.add(activity);
        user.setActivities(activities);
        return activityRepository.save(activity);
    }

    @Override
    public Activity addByType(String type) {
        User user = getUser();

        JsonNode activityJson = feign.getTypeActivity(type);
        Activity activity = getActivity(activityJson);


        List<Activity> activities = user.getActivities();
        activities.add(activity);
        user.setActivities(activities);
        return activityRepository.save(activity);
    }

    @Override
    public Activity addByParticipants(String count) {
        User user = getUser();

        JsonNode activityJson = feign.getParticipantsActivity(count);
        Activity activity = getActivity(activityJson);


        List<Activity> activities = user.getActivities();
        activities.add(activity);
        user.setActivities(activities);
        return activityRepository.save(activity);
    }

    private Activity getActivity(JsonNode activityJson) {
        return Activity.builder()
                       .activity(activityJson.get("activity").asText())
                       .accessibility(activityJson.get("accessibility").asText())
                       .type(activityJson.get("type").asText())
                       .activityStatus(ActivityStatus.ACTIVE)
                       .participants(activityJson.get("participants").asText())
                       .price(activityJson.get("price").asText())
                       .build();
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByUsername(auth.getName());
        return userOptional.get();
    }


    @Override
    public boolean remove(Long id) {
        try {
            Optional<Activity> activityOptional = activityRepository.findById(id);
            Activity activity = activityOptional.get();
            activity.setActivityStatus(ActivityStatus.DELETED);
            activityRepository.save(activity);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> getAllByUser() {
        User user = getUser();
        List<Activity> activities = user.getActivities();
        activities.removeIf(activity -> activity.getActivityStatus() == ActivityStatus.DELETED);
        return activities;
    }

    @Override
    public Optional<Activity> get(Long id) {
        return activityRepository.findById(id);
    }

}
