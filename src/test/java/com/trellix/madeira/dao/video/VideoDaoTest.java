package com.trellix.madeira.dao.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.dao.video.VideoDao;
import com.madeira.dao.video.VideoRepository;
import com.madeira.entity.Video;

@ExtendWith(MockitoExtension.class)
public class VideoDaoTest {

    @InjectMocks
    VideoDao videoDao;

    @Mock
    VideoRepository videoRepository;

    @Test
    void testDeleteVideoById() {
        
    }

    @Test
    void testExistsVideoByName() {

        String mockName = "SF-Architecture";
        boolean mockResponse = true;

        doReturn(mockResponse).when(videoRepository).existsVideoByName(mockName);
        boolean actualResponse = videoDao.existsVideoByName(mockName);

        assertEquals(mockResponse, actualResponse);
    }

    @Test
    void testGetByVideoId() {
        UUID mockId = UUID.randomUUID();
        Video mockVideo = new Video();
        mockVideo.setVideoId(mockId);
        mockVideo.setName("SF-Architecture");
        mockVideo.setDescription("Snowflake MIgration Architecture by Alan");
        mockVideo.setRecordedDate(LocalDate.now());
        mockVideo.setLink("https://youtube.com");

        doReturn(Optional.of(mockVideo)).when(videoRepository).findById(mockId);
        Video actualResponse = videoDao.getByVideoId(mockId);

        assertEquals(actualResponse, mockVideo);
    }

    @Test
    void testSaveVideo() {
        UUID mockId = UUID.randomUUID();
        Video mockVideo = new Video();
        mockVideo.setVideoId(mockId);
        mockVideo.setName("SF-Architecture");
        mockVideo.setDescription("Snowflake MIgration Architecture by Alan");
        mockVideo.setRecordedDate(LocalDate.now());
        mockVideo.setLink("https://youtube.com");

        doReturn(mockVideo).when(videoRepository).save(mockVideo);
        UUID actualResponse = videoDao.saveVideo(mockVideo);

        assertEquals(actualResponse, mockId);
    }
}
