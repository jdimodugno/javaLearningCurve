package com.juandi.learningcurve;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
