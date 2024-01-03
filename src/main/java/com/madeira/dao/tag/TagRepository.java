package com.madeira.dao.tag;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.madeira.entity.Tag;
import com.madeira.entity.Video;

public interface TagRepository extends JpaRepository<Tag, UUID> {
 
    public boolean existsTagByName(String name);

    @Query("SELECT v from Video v JOIN v.tags t WHERE t.tagId in ( :ids )")
    public List<Video> findVideosByTagIdList(@Param("ids") List<UUID> ids);

}
