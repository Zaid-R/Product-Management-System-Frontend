package com.example.productmanagementfrontend.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig {
    
    @Bean
    public ViewScope viewScope() {
        return new ViewScope();
    }

    @Bean
    public static CustomScopeConfigurer scopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("view", new ViewScope());
        configurer.setScopes(scopes);
        return configurer;
    }
}
