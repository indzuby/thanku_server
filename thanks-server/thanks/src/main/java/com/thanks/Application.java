package com.thanks;

import com.thanks.repository.OrderInfoRepository;
import com.thanks.service.DatetimeService;
import com.thanks.service.impl.CurrentDatetimeService;
import com.thanks.util.provider.AuditingDateTimeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.GregorianCalendar;

@ComponentScan(basePackages = {
        "com.thanks.configure"
        , "com.thanks.controller"
        , "com.thanks.service"
        , "com.thanks.util.aspect"}
, basePackageClasses = {OrderInfoRepository.class})
@EnableJpaRepositories(basePackages = {"com.thanks.repository"})
@EnableAsync
@EnableTransactionManagement
@EnableSpringDataWebSupport
@SpringBootApplication
@EnableAutoConfiguration
@EnableRedisRepositories(basePackages = "com.thanks.repository")
@Slf4j
@EnableJpaAuditing
public class Application {

    @Bean
    public AuditorAware<Date> dateAuditorAware() {
        return () -> GregorianCalendar.getInstance().getTime();
    }

    @Bean
    public DateTimeProvider dateTimeProvider(DatetimeService datetimeService) {
        log.info("init date time provider");
        return new AuditingDateTimeProvider(datetimeService);
    }

    @Bean
    public DatetimeService currentDateTimeService() {
        return new CurrentDatetimeService();
    }

	public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
	}
}
