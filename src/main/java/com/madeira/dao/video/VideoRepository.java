package com.madeira.dao.video;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.madeira.entity.Video;

public interface VideoRepository extends JpaRepository<Video, UUID>{
    
    public boolean existsVideoByName(String name);

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM video v WHERE ( to_tsvector('english', v.name || ' ' || v.description) @@ to_tsquery( :toSearch ) )"
    )
    public List<Video> findVideosByTextSearch(@Param("toSearch") String toSearch);

}