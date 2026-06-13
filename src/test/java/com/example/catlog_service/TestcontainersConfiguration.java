package com.example.catlog_service;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    private final CatlogServiceApplication catlogServiceApplication;

    TestcontainersConfiguration(CatlogServiceApplication catlogServiceApplication) {
        this.catlogServiceApplication = catlogServiceApplication;
    }

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		return new PostgreSQLContainer(DockerImageName.parse("postgres:16-alpine"));
	}
	public static void main(String[] args) {
		SpringApplication.from(CatlogServiceApplication::main)
		.with(TestCatlogServiceApplication.class);
	}

}
