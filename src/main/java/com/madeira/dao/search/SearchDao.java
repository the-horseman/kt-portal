package com.madeira.dao.search;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.madeira.util.Builders.QueryBuilder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class SearchDao {
    
    private final SearchRepository searchRepository;

    public List<Object> getSearchResults(String textToSearch, List<String> searchIn) {
        QueryBuilder sqlQueryBuilder = new QueryBuilder()
            .addtextSearchFilter(textToSearch);
        if (searchIn.contains("video")) {
            sqlQueryBuilder = sqlQueryBuilder.addVideoSelectors()
                .addTagSelectors()
                .addProductSelectors()
                .addVideoDatastore()
                .addTagJoin()
                .addProductJoin();
        }
        String sqlString = sqlQueryBuilder.build();
        return searchRepository.executeQuery(sqlString);
    }

}
