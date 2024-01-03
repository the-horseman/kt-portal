package com.madeira.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.madeira.dto.search.MergeSearch;
import com.madeira.service.SearchService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/search")
@AllArgsConstructor
public class SearchController {

    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";
    private final SearchService searchService;

    @GetMapping(produces = APPLICATION_VND_API_JSON)
    @ResponseBody
    public List<MergeSearch> textSearch(
            @RequestParam(value = "textToSearch") String textToSearch,
            @RequestParam(value = "searchIn") List<String> searchIn) {
        return searchService.getSearchResults(textToSearch, searchIn);
    }

}