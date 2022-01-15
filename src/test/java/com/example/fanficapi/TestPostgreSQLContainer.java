package com.example.fanficapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
@Component
public class TestPostgreSQLContainer extends PostgreSQLContainer<TestPostgreSQLContainer> {

    public static final String POSTGRES_IMAGE = "postgres:14.1";

    public TestPostgreSQLContainer() {
        super(POSTGRES_IMAGE);
        withDatabaseName("fanficTest");
        withUsername("user");
        withPassword("pass");
    }

    @Override
    public void start() {
        super.start();
        log.info("Test DataBase container {} was started ...", POSTGRES_IMAGE);
    }

    @Override
    public void stop() {
        log.info("Test DataBase container {} was stopped ...", POSTGRES_IMAGE);
    }
}
