package com.madeira.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.madeira.dao.tag.TagDao;
import com.madeira.dto.tag.CreateTagRequest;
import com.madeira.dto.tag.CreateTagResponse;
import com.madeira.entity.Product;
import com.madeira.entity.Tag;
import com.madeira.entity.Video;
import com.madeira.exception.ConflictException;
import com.madeira.exception.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TagService {
    
    final static private String TAG_NOT_FOUND_MESSAGE = "The Tag was not found";
    final static private String TAG_CREATION_MESSGAE = "The Tag has been created";
    final static private String TAG_TAKEN_MESSAGE = "The Tag name is already taken";
    final private TagDao tagDao;
    final private ProductService productService;

    public CreateTagResponse addTag(CreateTagRequest tagRequest) {
        if (tagDao.existsTagByName(tagRequest.getName())) {
            throw new ConflictException(TAG_TAKEN_MESSAGE);
        }
        Tag tag = new Tag(
            tagRequest.getName(),
            tagRequest.getDescription()
        );
        tag = this.addProductsToTag(tag, tagRequest.getProducts());
        UUID tagId = tagDao.saveTag(tag);
        List<UUID> addedProducts = tag.getProducts().stream()
                .map(product -> {return product.getProductId(); })
                .toList();
        return new CreateTagResponse(
            tagId,
            TAG_CREATION_MESSGAE,
            addedProducts
        );
    }

    public Tag patchTag(UUID id, CreateTagRequest patch) {
        Tag tag = this.getTagById(id);
        tag.setName(patch.getName());
        tag.setDescription(patch.getDescription());
        this.addProductsToTag(tag, patch.getProducts());
        tagDao.saveTag(tag);
        return tag;
    }

    public List<Tag> getTags() {
        return tagDao.getAllTag();
    }

    public Tag getTagById(UUID id) {
        try {
            return tagDao.getByTagId(id);
        } catch (Exception e) {
            throw new NotFoundException(TAG_NOT_FOUND_MESSAGE);
        }
    }

    public Tag addProductsToTag(Tag tag, List<UUID> products) {
        Set<Product> savedProducts = new HashSet<>();
        for (UUID id : products) {
            Product currentProduct = productService.getProductById(id);
            savedProducts.add(currentProduct);
        }
        tag.setProducts(new ArrayList<>(savedProducts));
        return tag;
    }

    public List<Video> getVideosByTagIdList(List<UUID> ids) {
        return tagDao.getVideosByTagIdList(ids);
    }

}
