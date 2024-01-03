package com.trellix.madeira.dao.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.dao.tag.TagDao;
import com.madeira.dao.tag.TagRepository;
import com.madeira.entity.Tag;

@ExtendWith(MockitoExtension.class)
public class TagDaoTest {

    @InjectMocks
    TagDao tagDao;

    @Mock
    TagRepository tagRepository;

    @Test
    void testDeleteTagById() {
        UUID mockId = UUID.randomUUID();

        tagDao.deleteTagById(mockId);
    }

    @Test
    void testExistsTagByName() {

        boolean mockResponse = true;
        String mockName = "Snowflake";

        when(tagRepository.existsTagByName(mockName)).thenReturn(mockResponse);
        boolean actualResponse = tagDao.existsTagByName(mockName);

        assertEquals(actualResponse, mockResponse);

    }

    @Test
    void testGetByTagId() {

        UUID mockId = UUID.randomUUID();
        Tag mockTag = new Tag();
        Optional<Tag> mockTagOptional = Optional.of(mockTag);

        when(tagRepository.findById(mockId)).thenReturn(mockTagOptional);
        Tag actualResponse = tagDao.getByTagId(mockId);
        
        assertEquals(actualResponse, mockTag);
    }

    @Test
    void testGetAllTag() {
        Tag mockTag = new Tag();
        List<Tag> mockTags = List.of(mockTag);

        when(tagRepository.findAll()).thenReturn(mockTags);
        List<Tag>actualResponse = tagDao.getAllTag();

        assertEquals(actualResponse, mockTags);
    }

    @Test
    void testSaveTag() {

        UUID mockId = UUID.randomUUID();
        Tag mockTag = new Tag();
        mockTag.setTagId(mockId);
        mockTag.setName("Snowflake");
        mockTag.setDescription("EDR data storage");
        mockTag.setProducts(Collections.emptyList());

        when(tagRepository.save(mockTag)).thenReturn(mockTag);
        UUID actualResponseId = tagDao.saveTag(mockTag);

        assertEquals(actualResponseId, mockId);
    }
}
