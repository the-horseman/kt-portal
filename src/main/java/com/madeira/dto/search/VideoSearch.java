package com.madeira.dto.search;

import java.util.UUID;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VideoSearch {
    
    private UUID videoId;

    private LocalDate recordedDate;

    private String videoName;
    
    private String videoDescription;

    private String link; 

}
