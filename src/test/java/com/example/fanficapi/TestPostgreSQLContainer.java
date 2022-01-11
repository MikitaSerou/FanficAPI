package com.example.fanficapi;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
public class TestPostgreSQLContainer extends PostgreSQLContainer<TestPostgreSQLContainer> {

    public static final String POSTGRES_IMAGE = "postgres:14.1";
    private static TestPostgreSQLContainer container;

    private TestPostgreSQLContainer() {
        super(POSTGRES_IMAGE);
    }

    public static TestPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new TestPostgreSQLContainer()
                    .withDatabaseName("fanficTest")
                    .withUsername("user")
                    .withPassword("pass");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        log.info("Test DataBase container was started ...");
    }

    @Override
    public void stop() {
        log.info("Test DataBase container was stopped ...");
    }
}
