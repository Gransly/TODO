package com.eatnow.service;

import com.eatnow.models.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityService {
    Activity addByRandom();
    Activity addByType(String type);
    Activity addByParticipants(String cout);
    boolean remove(Long id);
    List<Activity> getAll();
    List<Activity> getAllByUser();
    Optional<Activity> get(Long id);
}
