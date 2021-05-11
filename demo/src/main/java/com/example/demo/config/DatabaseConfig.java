package com.example.demo.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:com/example/demo/config/application.properties")
public class DatabaseConfig {
    @Bean
    public DataSource dataSource(
        @Value("${spring.datasource.driver-class-name}") String jdbcDriver,
        @Value("${spring.datasource.url}") String jdbcUrl,
        @Value("${spring.datasource.username}") String jdbcUsername,
        @Value("${spring.datasource.password}") String jdbcPassword) {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName(jdbcDriver);
            ds.setUrl(jdbcUrl);
            ds.setUsername(jdbcUsername);
            ds.setPassword(jdbcPassword);
            return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
