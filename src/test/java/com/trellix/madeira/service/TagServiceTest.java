package com.trellix.madeira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.madeira.dao.tag.TagDao;
import com.madeira.dto.tag.CreateTagRequest;
import com.madeira.dto.tag.CreateTagResponse;
import com.madeira.entity.Tag;
import com.madeira.service.ProductService;
import com.madeira.service.TagService;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @InjectMocks
    TagService tagService;

    @Mock
    TagDao tagDao;

    @Mock
    ProductService productService;

    @Test
    void testAddTag() {

        TagService spyTagService = spy(tagService);
        List<UUID> emptyList = Collections.emptyList();

        CreateTagRequest mockTagRequest = new CreateTagRequest();
        mockTagRequest.setName("Snowflake");
        mockTagRequest.setDescription("EDR data storage");
        mockTagRequest.setProducts(emptyList);
        UUID mockId = UUID.randomUUID(); 

        Tag mockTag = new Tag(
            mockTagRequest.getName(),
            mockTagRequest.getDescription()
        );
        
        CreateTagResponse mockTagResponse = new CreateTagResponse();
        mockTagResponse.setId(mockId);
        mockTagResponse.setMessage("The Tag has been created");
        mockTagResponse.setAddedProducts(emptyList);

        when(tagDao.existsTagByName("Snowflake")).thenReturn(false);
        doReturn(mockId).when(tagDao).saveTag(mockTag);
        doReturn(mockTag).when(spyTagService).addProductsToTag(mockTag, emptyList);
        CreateTagResponse actualTagResponse = spyTagService.addTag(mockTagRequest);

        assertEquals(actualTagResponse, mockTagResponse);

    }

    @Test
    void testGetTags() {

        Tag mockTag = new Tag();
        mockTag.setTagId(UUID.randomUUID());
        mockTag.setName("Snowflake");
        mockTag.setDescription("EDR data storage");
        mockTag.setProducts(Collections.emptyList());

        List<Tag> mockResponseTags = List.of(mockTag);

        when(tagDao.getAllTag()).thenReturn(mockResponseTags);

        List<Tag> actualResponseTags = tagService.getTags();

        assertEquals(actualResponseTags, mockResponseTags);

    }

    @Test
    void testAddProductsToTag() {

        UUID mockId = UUID.randomUUID();
        Tag mockTag = new Tag();
        mockTag.setTagId(mockId);
        mockTag.setName("Snowflake");
        mockTag.setDescription("EDR data storage");
        mockTag.setProducts(Collections.emptyList());

        Tag actualResponse = tagService.addProductsToTag(mockTag, Collections.emptyList());
        assertEquals(actualResponse, mockTag);
    }

}
