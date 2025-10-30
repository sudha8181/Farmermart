package com.tek.FarmerMart.UserMgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorProvider")
public class MangoConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new com.tek.FarmerMart.UserMgmt.audit.AuditorAwareImpl();
    }

}
