package com.hong.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
// import org.apache.commons.dbcp2.BasicDataSource;


@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceCfg2 {

    @Value("${driver}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${dbuser}")
    private String username;

    @Value("${dbpassword}")
    private String password;

    @Value("${init-db:false}")
    private String initDatabase;

    // @Bean
    // public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
    //     return new PropertySourcesPlaceholderConfigurer();
    // }

    // @Bean(destroyMethod = "close")
    @Bean
    public DataSource dataSource() {
        // BasicDataSource dataSource = new BasicDataSource();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
        return dataSourceInitializer;
    }

}