package com.hong.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({DataSourceCfg.class, TransactionCfg.class})
@Configuration
// @ComponentScan(basePackages = {"com.hong.spring"})
@ComponentScan(basePackages = {"com.hong.spring.dao", "com.hong.spring.service"})
public class AppConfig {
}
