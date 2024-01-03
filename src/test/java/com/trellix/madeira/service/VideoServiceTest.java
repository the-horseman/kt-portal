package com.trellix.madeira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.dao.video.VideoDao;
import com.madeira.dto.video.CreateVideoRequest;
import com.madeira.dto.video.CreateVideoResponse;
import com.madeira.entity.Video;
import com.madeira.service.TagService;
import com.madeira.service.VideoService;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @InjectMocks
    VideoService videoService;

    @Mock
    VideoDao videoDao;

    @Mock
    TagService tagService;

    @Test
    void testAddTagsToVideo() {
        Video mockVideo = new Video();
        mockVideo.setName("SF-Architecture");
        mockVideo.setDescription("Snowflake MIgration Architecture by Alan");
        mockVideo.setRecordedDate(LocalDate.now());
        mockVideo.setLink("https://youtube.com");

        Video actualResponse = videoService.addTagsToVideo(mockVideo, List.of());

        assertEquals(actualResponse, mockVideo);
    }

    @Test
    void testAddVideo() {
        
        VideoService spyVideoService = spy(videoService);
        CreateVideoRequest mockRequest = new CreateVideoRequest();
        mockRequest.setName("SF-Architecture");
        mockRequest.setDescription("Snowflake MIgration Architecture by Alan");
        mockRequest.setRecordedDate(LocalDate.now());
        mockRequest.setLink("https://youtube.com");
        mockRequest.setTags(List.of());
        
        UUID mockId = UUID.randomUUID();
        Video mockVideo = new Video();
        mockVideo.setName(mockRequest.getName());
        mockVideo.setDescription(mockRequest.getDescription());
        mockVideo.setRecordedDate(LocalDate.now());
        mockVideo.setLink(mockRequest.getLink());

        CreateVideoResponse mockResponse = new CreateVideoResponse();
        mockResponse.setId(mockId);
        mockResponse.setMessage("The Video has been created");
        mockResponse.setAddedTags(List.of());

        doReturn(false).when(videoDao).existsVideoByName(mockRequest.getName());
        doReturn(mockVideo).when(spyVideoService).addTagsToVideo(mockVideo, mockRequest.getTags());
        doReturn(mockId).when(videoDao).saveVideo(mockVideo);
        CreateVideoResponse actualResponse = spyVideoService.addVideo(mockRequest);

        assertEquals(actualResponse, mockResponse);
    }

    @Test
    void testGetVideoById() {
        UUID mockId = UUID.randomUUID();
        Video mockVideo = new Video();
        mockVideo.setVideoId(mockId);
        mockVideo.setName("SF-Architecture");
        mockVideo.setDescription("Snowflake MIgration Architecture by Alan");
        mockVideo.setRecordedDate(LocalDate.now());
        mockVideo.setLink("https://youtube.com");

        doReturn(mockVideo).when(videoDao).getByVideoId(mockId);
        Video actualResponse = videoService.getVideoById(mockId);

        assertEquals(actualResponse, mockVideo);
    }

    @Test
    void testPatchVideo() {

        VideoService spyVideoService = spy(videoService);

        CreateVideoRequest mockRequest = new CreateVideoRequest();
        mockRequest.setName("SF-Architecture");
        mockRequest.setDescription("Snowflake MIgration Architecture by Alan");
        mockRequest.setRecordedDate(LocalDate.now());
        mockRequest.setLink("https://youtube.com");
        mockRequest.setTags(List.of());

        UUID mockId = UUID.randomUUID();
        Video mockVideo = new Video();
        mockVideo.setVideoId(mockId);
        mockVideo.setName(mockRequest.getName());
        mockVideo.setDescription(mockRequest.getDescription());
        mockVideo.setRecordedDate(mockRequest.getRecordedDate());
        mockVideo.setLink(mockRequest.getLink());

        doReturn(mockVideo).when(spyVideoService).getVideoById(mockId);
        doReturn(mockVideo).when(spyVideoService).addTagsToVideo(mockVideo, List.of());
        doReturn(mockId).when(videoDao).saveVideo(mockVideo);
        Video actualResponse = spyVideoService.patchVideo(mockId, mockRequest);

        assertEquals(actualResponse, mockVideo);
    }
}
