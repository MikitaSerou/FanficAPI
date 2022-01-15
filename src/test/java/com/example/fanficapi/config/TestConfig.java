package com.example.fanficapi.config;

import com.example.fanficapi.TestPostgreSQLContainer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration
@Slf4j
@Profile("test")
public class TestConfig {

    @Autowired
    private PostgreSQLContainer<TestPostgreSQLContainer> testPostgreSQLContainer;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public JdbcDatabaseContainer<?> jdbcDatabaseContainer() {
        return testPostgreSQLContainer;
    }

    @Bean(name = "testDataSource")
    public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcDatabaseContainer.getJdbcUrl());
        hikariConfig.setUsername(jdbcDatabaseContainer.getUsername());
        hikariConfig.setPassword(jdbcDatabaseContainer.getPassword());
        log.info("jdbcUrl: {}", hikariConfig.getJdbcUrl());
        log.info("username: {}", hikariConfig.getUsername());
        log.info("password: {}", hikariConfig.getPassword());

        return new HikariDataSource(hikariConfig);
    }
}
