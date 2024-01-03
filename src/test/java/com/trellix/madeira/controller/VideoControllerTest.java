package com.trellix.madeira.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.controller.VideoController;
import com.madeira.dto.video.CreateVideoRequest;
import com.madeira.dto.video.CreateVideoResponse;
import com.madeira.entity.Video;
import com.madeira.service.VideoService;

@ExtendWith(MockitoExtension.class)
public class VideoControllerTest {

    @InjectMocks
    VideoController videoController;

    @Mock
    VideoService videoService;

    @Test
    void testCreateVideo() {
        UUID tagId = UUID.randomUUID();
        CreateVideoRequest mockRequest = new CreateVideoRequest();
        mockRequest.setName("SF-Architecture");
        mockRequest.setDescription("Snowflake MIgration Architecture by Alan");
        mockRequest.setRecordedDate(LocalDate.now());
        mockRequest.setLink("https://youtube.com");
        mockRequest.setTags(List.of(tagId));

        CreateVideoResponse mockResponse = new CreateVideoResponse();
        mockResponse.setAddedTags(List.of(tagId));
        mockResponse.setMessage("The Video has been created");

        doReturn(mockResponse).when(videoService).addVideo(mockRequest);

        CreateVideoResponse actualResponse = videoController.createVideo(mockRequest);
        assertEquals(actualResponse, mockResponse);
    }

    @Test
    void testPatchVideo() {

        UUID videoId = UUID.randomUUID();
        CreateVideoRequest mockRequest = new CreateVideoRequest();
        mockRequest.setName("SF-Architecture");
        mockRequest.setDescription("Snowflake MIgration Architecture by Alan");
        mockRequest.setRecordedDate(LocalDate.now());
        mockRequest.setLink("https://youtube.com");
        mockRequest.setTags(List.of());

        Video mockVideo = new Video();
        mockVideo.setVideoId(videoId);
        mockVideo.setName(mockRequest.getName());
        mockVideo.setDescription(mockRequest.getDescription());
        mockVideo.setRecordedDate(mockRequest.getRecordedDate());
        mockVideo.setLink(mockRequest.getLink());
        mockVideo.setTags(List.of());

        doReturn(mockVideo).when(videoService).patchVideo(videoId, mockRequest);
        Video actualPatchResponse = videoController.patchVideo(videoId, mockRequest);

        assertEquals(actualPatchResponse, mockVideo);

    }
}
