package com.thanks;

import com.sun.tools.javac.sym.Profiles;
import com.thanks.service.DatetimeService;
import com.thanks.service.impl.CurrentDatetimeServcie;
import com.thanks.util.provider.AuditingDateTimeProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {
        "com.thanks.configure"
        , "com.thanks.controller"
        , "com.thanks.service"
        , "com.thanks.util.aspect"})
@EnableJpaRepositories(basePackages = {"com.thanks.repository"})
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableAsync
@EnableTransactionManagement
@EnableSpringDataWebSupport
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    @Bean
    DateTimeProvider dateTimeProvider(DatetimeService datetimeService) {
        return new AuditingDateTimeProvider(datetimeService);
    }

    @Bean
    DatetimeService currentDateTimeService() {
        return new CurrentDatetimeServcie();
    }

	public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
	}
}
