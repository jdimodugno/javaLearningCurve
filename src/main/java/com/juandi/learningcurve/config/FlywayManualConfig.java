package com.juandi.learningcurve.config;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayManualConfig {

  @Bean(initMethod = "migrate")
  Flyway flyway(DataSource dataSource) {
    return Flyway.configure()
        .dataSource(dataSource)
        .locations("classpath:db/migration")
        .defaultSchema("public")
        .schemas("public")
        .load();
  }
}