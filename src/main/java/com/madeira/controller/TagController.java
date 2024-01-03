package com.madeira.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.madeira.dto.tag.CreateTagRequest;
import com.madeira.dto.tag.CreateTagResponse;
import com.madeira.entity.Tag;
import com.madeira.entity.Video;
import com.madeira.service.TagService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/tags")
@AllArgsConstructor
public class TagController {
    
    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";
    final private TagService tagService;

    @PostMapping(consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public CreateTagResponse createTag(
        @Valid @RequestBody CreateTagRequest createTagRequest
    ) {
        return tagService.addTag(createTagRequest);
    }

    @PatchMapping(path = "/{id}", consumes = APPLICATION_VND_API_JSON, produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public Tag patchTag(
        @PathVariable(value = "id") UUID id, 
        @Valid @RequestBody CreateTagRequest patch
    ) {
        return tagService.patchTag(id, patch);
    }

    @GetMapping(produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public List<Tag> getTags() {
        return tagService.getTags();
    }

    @GetMapping(path = "/videos", produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public List<Video> getVideos(
        @RequestParam(value = "ids") List<UUID> ids
    ) {
        return tagService.getVideosByTagIdList(ids);
    }
    
}
