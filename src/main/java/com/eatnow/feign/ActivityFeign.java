package com.eatnow.feign;

import com.eatnow.models.Activity;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient(value = "Bored", url = "https://www.boredapi.com/api/")
public interface ActivityFeign {
    @GetMapping("/activity")
    JsonNode getRandomActivity();

    @GetMapping("/activity")
    JsonNode getTypeActivity(@RequestParam String type);

    @GetMapping("/activity")
    JsonNode getParticipantsActivity(@RequestParam String participants);
}
