package com.madeira.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MergeSearch {
    
    private VideoSearch videoSearch;

    private TagSearch tagSearch;
    
    private ProductSearch productSearch;

}
