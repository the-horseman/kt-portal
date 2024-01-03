package com.madeira.dto.search;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TagSearch {
    
    public UUID tagId;

    public String tagName;

}
