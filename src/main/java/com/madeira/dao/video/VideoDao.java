package com.madeira.dao.video;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.madeira.entity.Video;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class VideoDao {
    
    final private VideoRepository videoRepository;

    public UUID saveVideo(Video video) {
        Video savedVideo = videoRepository.save(video);
        return savedVideo.getVideoId();
    }

    public boolean existsVideoByName(String name) {
        return videoRepository.existsVideoByName(name);
    }

    public Video getByVideoId(UUID id) {
        Video video = videoRepository.findById(id).get();
        return video;
    }

    public void deleteVideoById(UUID id) {
        videoRepository.deleteById(id);
    }

}
