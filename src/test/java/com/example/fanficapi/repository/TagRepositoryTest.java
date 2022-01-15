package com.example.fanficapi.repository;

import com.example.fanficapi.config.TestConfig;
import com.example.fanficapi.model.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;
    @Container
    @Autowired
    private PostgreSQLContainer<?> POSTGRESQL_CONTAINER;

    @DisplayName("Container running status test in TagRepositoryTest.class")
    @Test
    void containerIsRunning() {
        assertThat(POSTGRESQL_CONTAINER.isRunning(), is(true));
    }

    @DisplayName("Find tags by theme ID")
    @Test
    void findByThemeId() {
        Set<Tag> tagsByThemeId = tagRepository.findByThemeId(1);
        tagsByThemeId.forEach(tag -> log.info("{}", tag));

        assertThat(tagsByThemeId, not(empty()));
        assertThat(tagsByThemeId.size(), is(5));
        assertThat(tagsByThemeId.contains(new Tag(14L, "Fantastic")), is(true));
        assertThat(tagsByThemeId.contains(new Tag(1L, "Scary")), is(true));
        assertThat(tagsByThemeId.contains(new Tag(12L, "Fantasy")), is(true));
        assertThat(tagsByThemeId.contains(new Tag(13L, "Horror")), is(true));
        assertThat(tagsByThemeId.contains(new Tag(16L, "Stephen King")), is(true));
        assertThat(tagsByThemeId.contains(new Tag(9L, "RTS")), is(false));
    }
}
