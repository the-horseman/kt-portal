package com.madeira.dao.tag;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.madeira.entity.Tag;
import com.madeira.entity.Video;
import com.madeira.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TagDao {

    final static private String TAG_NOT_FOUND_MESSAGE = "The Tag was not found";
    final private TagRepository tagRepository;
    
    public UUID saveTag(Tag tag) {
        Tag savedTag = tagRepository.save(tag);
        return savedTag.getTagId();
    }

    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    public boolean existsTagByName(String name) {
        return tagRepository.existsTagByName(name);
    }

    public void deleteTagById(UUID id) {
        tagRepository.deleteById(id);
    }

    public Tag getByTagId(UUID id) {
        try {
            Tag tag = tagRepository.findById(id).get();
            return tag; 
        } catch (Exception e) {
            throw new NotFoundException(TAG_NOT_FOUND_MESSAGE);
        }
    }

    public List<Video> getVideosByTagIdList(List<UUID> ids) {
        return tagRepository.findVideosByTagIdList(ids);
    }

}
