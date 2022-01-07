package com.example.fanficapi.repository;

import com.example.fanficapi.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT * from tag WHERE id IN (" +
            "SELECT tag_id FROM publication_tag where publication_Id IN (" +
            "SELECT id FROM publication WHERE theme_id = :themeId))",
            nativeQuery = true)
    Set<Tag> findByThemeId(@Param("themeId") Integer themeId);
}
