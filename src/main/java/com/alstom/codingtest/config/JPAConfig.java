package com.alstom.codingtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.alstom.codingtest.repository")
public class JPAConfig {
}
