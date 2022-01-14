package com.example.fanficapi.repository;

import com.example.fanficapi.TestPostgreSQLContainer;
import com.example.fanficapi.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
class ThemeRepositoryTest {

    @Autowired
    private ThemeRepository themeRepository;
    @Container
    private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = TestPostgreSQLContainer.getInstance();

    @DisplayName("Container running status test in ThemeRepositoryTest.class")
    @Test
    void containerIsRunning() {
        assertThat(POSTGRESQL_CONTAINER.isRunning(), is(true));
    }

    @DisplayName("Get count of subscribers by theme ID")
    @Test
    void countOfSubscribersByThemeId() {
        assertThat(themeRepository.countOfSubscribersByThemeId(1), is(2L));
        assertThat(themeRepository.countOfSubscribersByThemeId(2), is(0L));
        assertThat(themeRepository.countOfSubscribersByThemeId(6), is(1L));
    }
}
