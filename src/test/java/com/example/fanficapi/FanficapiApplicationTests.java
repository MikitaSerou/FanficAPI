package com.example.fanficapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Slf4j
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
class FanficapiApplicationTests {


    @Value("${spring.flyway.enabled}")
    String flywayEnabledStatus;

    @AfterAll
    static void afterAll() {
        log.info("Context loaded successfully");
    }

    @DisplayName("Test if application is up and running")
    @Test
    void entireContextLoadsWithNoExceptions() {
        log.info("Context loading test");
        log.info("Flyway Enabled Status: {}", flywayEnabledStatus);
    }
}
