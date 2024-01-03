package com.trellix.madeira.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.controller.TagController;
import com.madeira.dto.tag.CreateTagRequest;
import com.madeira.dto.tag.CreateTagResponse;
import com.madeira.entity.Tag;
import com.madeira.service.TagService;


@ExtendWith(MockitoExtension.class)
public class TagControllerTest {

    @InjectMocks
    TagController tagController;

    @Mock
    TagService tagService;

    @Test
    void testCreateTag() {

        CreateTagRequest mockTagRequest = new CreateTagRequest();
        mockTagRequest.setName("Snowflake");
        mockTagRequest.setDescription("EDR data storage");
        mockTagRequest.setProducts(Collections.emptyList());

        CreateTagResponse mockTagResponse = new CreateTagResponse();
        mockTagResponse.setId(UUID.randomUUID());
        mockTagResponse.setMessage("The Tag has been created");
        mockTagResponse.setAddedProducts(Collections.emptyList());
        
        when(tagService.addTag(mockTagRequest)).thenReturn(mockTagResponse);
        CreateTagResponse actualTagResponse = tagController.createTag(mockTagRequest);

        assertEquals(actualTagResponse, mockTagResponse);
    }

    @Test
    void testGetTags() {

        Tag mockTag = new Tag();
        mockTag.setName("Snowflake");
        mockTag.setDescription("EDR data storage");
        mockTag.setProducts(Collections.emptyList());

        List<Tag> mockResponseTags = List.of(mockTag);

        when(tagService.getTags()).thenReturn(mockResponseTags);
        List<Tag> actualResponseTags = tagController.getTags();

        assertEquals(actualResponseTags, mockResponseTags);

    }
}
