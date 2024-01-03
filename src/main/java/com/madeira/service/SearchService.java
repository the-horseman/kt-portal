package com.madeira.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.madeira.dao.search.SearchDao;
import com.madeira.dto.search.MergeSearch;
import com.madeira.util.Mapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SearchService {
    
    private final SearchDao searchDao;
    private final Mapper mapper;

    public List<MergeSearch> getSearchResults(String textToSearch, List<String> searchIn) {
        List<Object> searchObjects = searchDao.getSearchResults(textToSearch, searchIn);
        List<MergeSearch> searchResults = searchObjects.stream()
                                        .map(searchObject -> mapper.toMergeSearchDTO(searchObject))
                                        .toList(); 
        return searchResults;
    }
}
