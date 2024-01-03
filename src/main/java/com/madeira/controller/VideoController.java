package com.madeira.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.madeira.dto.video.CreateVideoRequest;
import com.madeira.dto.video.CreateVideoResponse;
import com.madeira.entity.Video;
import com.madeira.service.VideoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/videos")
@AllArgsConstructor
public class VideoController {
    
    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";
    final private VideoService videoService;
    
    @PostMapping(consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public CreateVideoResponse createVideo(
        @Valid @RequestBody CreateVideoRequest videoRequest
    ) {
        return videoService.addVideo(videoRequest);
    }

    @PatchMapping(path = "/{id}", consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public Video patchVideo(
        @PathVariable(value = "id") UUID id, 
        @Valid @RequestBody CreateVideoRequest patch
    ) {
        return videoService.patchVideo(id, patch);
    }

}
