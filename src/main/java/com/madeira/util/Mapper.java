package com.madeira.util;

import java.util.UUID;
import java.sql.Date;

import org.springframework.stereotype.Component;

import com.madeira.dto.search.MergeSearch;
import com.madeira.dto.search.ProductSearch;
import com.madeira.dto.search.TagSearch;
import com.madeira.dto.search.VideoSearch;

@Component
public class Mapper {

    public VideoSearch toVideoSearchDTO(Object[] searchObject) {
        return new VideoSearch(
            (UUID) searchObject[0], 
            ((Date)searchObject[1]).toLocalDate(), 
            (String) searchObject[2],
            (String) searchObject[3], 
            (String) searchObject[4]);
    }

    public TagSearch toTagSearchDTO(Object[] searchObject) {
        return new TagSearch(
            (UUID) searchObject[5],
            (String) searchObject[6]);
    }

    public ProductSearch toProductSearchDTO(Object[] searchObject) {
        return new ProductSearch(
            (UUID) searchObject[7],
            (String) searchObject[8]);
    }

    public MergeSearch toMergeSearchDTO(Object textSearch) {
        return new MergeSearch(
            toVideoSearchDTO((Object[]) textSearch),
            toTagSearchDTO((Object[]) textSearch),
            toProductSearchDTO((Object[]) textSearch));
    }

}
