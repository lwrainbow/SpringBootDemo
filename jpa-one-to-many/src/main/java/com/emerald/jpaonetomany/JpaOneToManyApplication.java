package com.emerald.jpaonetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaOneToManyApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyApplication.class, args);
	}
}
