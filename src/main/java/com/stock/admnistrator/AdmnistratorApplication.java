package com.stock.admnistrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.stock.admnistrator")
@EnableJpaRepositories(basePackages = {"com.stock.admnistrator.repositories"})
@EntityScan(basePackages = { "com.stock.admnistrator.model"})
public class AdmnistratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmnistratorApplication.class, args);
	}

}
