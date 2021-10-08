package ru.cherry.springhomework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.cherry.springhomework.h2.dao")
@EnableMongoRepositories(basePackages = "ru.cherry.springhomework.mongo.dao")
public class Config {
}
