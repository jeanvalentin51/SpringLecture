package com.valentin.lecture.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"local"})
public class DataSourceLocal {

    /* set VM options in config: -Dspring.profiles.active=local
     * set environment variables in config for username/pwd/url
     */

    private static final String USER_NAME = System.getenv("unlocal");
    private static final String USER_PWD = System.getenv("pwdlocal");
    private static final String URL = System.getenv("urllocal");

    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(USER_NAME);
        hikariConfig.setPassword(USER_PWD);
        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return new HikariDataSource(hikariConfig);
    }
}
