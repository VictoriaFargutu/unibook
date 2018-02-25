package com.victoria.fargutu.unibook.api;

import com.victoria.fargutu.unibook.repository.configuration.MyAppJpaConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Import({MyAppJpaConfiguration.class})
@ComponentScan({"com.victoria.fargutu.unibook.repository",
        "com.victoria.fargutu.unibook.service",
        "com.victoria.fargutu.unibook.api"})
public class MyApplication extends SpringBootServletInitializer {
}
