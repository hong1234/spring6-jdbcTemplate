package com.hong.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;

import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
// @ComponentScan("com.hong.spring.dao")
@PropertySource("classpath:database.properties")
public class DataSourceCfg {

	@Autowired
	Environment env;

	// @Bean
    // public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    //     return new JdbcTemplate(dataSource);
    // }

	@Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setUsername(env.getProperty("dbuser"));
		dataSource.setPassword(env.getProperty("dbpassword"));
		return dataSource;
	}

	@Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("data.sql"));  // database init per sql file
		
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(Boolean.parseBoolean(env.getProperty("init-db")));
        return dataSourceInitializer;
    }

}