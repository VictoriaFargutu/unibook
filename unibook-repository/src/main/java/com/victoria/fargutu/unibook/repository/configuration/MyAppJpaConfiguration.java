package com.victoria.fargutu.unibook.repository.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories("com.victoria.fargutu.unibook.repository")
@EntityScan("com.victoria.fargutu.unibook.repository.model")
@Configuration
public class MyAppJpaConfiguration {
}
