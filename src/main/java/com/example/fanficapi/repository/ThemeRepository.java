package com.example.fanficapi.repository;

import com.example.fanficapi.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    @Query(value = "SELECT count(*) from users_theme WHERE theme_id = :themeId", nativeQuery = true)
    Long countOfSubscribersByThemeId(@Param("themeId") Integer themeId);
}
