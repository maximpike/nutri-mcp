package com.maximpike.nutrimc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * NutriMCP - Intelligent Meal Planning Assistant
 *
 * A Spring Boot application that integrates with Anthropic's Model Context Protocol (MCP)
 * to provide intelligent meal planning based on nutritional data from various food APIs.
 *
 * Key Features:
 * - Meal planning based on dietary preferences
 * - MCP integration for food/nutrition data
 * - RESTful API for meal plan management
 * - Infrastructure as Code deployment
 *
 * @author maximpike
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class NutrimcApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutrimcApplication.class, args);
    }
}