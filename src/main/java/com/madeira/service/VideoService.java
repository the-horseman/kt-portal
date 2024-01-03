package com.madeira.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.madeira.dao.video.VideoDao;
import com.madeira.dto.video.CreateVideoRequest;
import com.madeira.dto.video.CreateVideoResponse;
import com.madeira.entity.Tag;
import com.madeira.entity.Video;
import com.madeira.exception.ConflictException;
import com.madeira.exception.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class VideoService {
    
    final static private String VIDEO_NOT_FOUND_MESSAGE = "The Video was not found";
    final static private String VIDEO_CREATION_MESSGAE = "The Video has been created";
    final static private String VIDEO_TAKEN_MESSAGE = "The Video name is already taken";
    final private VideoDao videoDao;
    final private TagService tagService;

    public CreateVideoResponse addVideo(CreateVideoRequest videoRequest) {
        if (videoDao.existsVideoByName(videoRequest.getName())) {
            throw new ConflictException(VIDEO_TAKEN_MESSAGE);
        }
        Video video = new Video(
            videoRequest.getName(),
            videoRequest.getDescription(),
            videoRequest.getLink(),
            videoRequest.getRecordedDate()
        );
        video = this.addTagsToVideo(video, videoRequest.getTags());
        UUID videoId = videoDao.saveVideo(video);
        List<UUID> addedTags = video.getTags().stream()
                .map(tag -> { return tag.getTagId(); })
                .toList();
        return new CreateVideoResponse(
            videoId,
            VIDEO_CREATION_MESSGAE,
            addedTags
        );
    }

    public Video patchVideo(UUID id, CreateVideoRequest patch) {
        Video video = this.getVideoById(id);
        video.setName(patch.getName());
        video.setDescription(patch.getDescription());
        video.setLink(patch.getLink());
        video.setRecordedDate(patch.getRecordedDate());
        video = this.addTagsToVideo(video, patch.getTags());
        videoDao.saveVideo(video);
        return video;
    }

    public Video getVideoById(UUID id) {
        try {
            return videoDao.getByVideoId(id);
        } catch (Exception e) {
            throw new NotFoundException(VIDEO_NOT_FOUND_MESSAGE);
        }
    }

    public Video addTagsToVideo(Video video, List<UUID> tags) {
        
        Set<Tag> savedTags = new HashSet<>();
        for (UUID id : tags) {
            Tag currentTag = tagService.getTagById(id);
            savedTags.add(currentTag);
        }
        video.setTags(new ArrayList<>(savedTags));
        return video;
    }

}