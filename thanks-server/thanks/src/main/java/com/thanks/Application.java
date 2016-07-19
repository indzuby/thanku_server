package com.thanks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"com.thanks.configure", "com.thanks.controller", "com.thanks.service"})
@EnableJpaRepositories(basePackages = {"com.thanks.repository"})
@EnableJpaAuditing
@EnableAsync
@EnableTransactionManagement
@EnableSpringDataWebSupport
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
	}
}
