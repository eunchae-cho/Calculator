package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demo.dao")
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(
        DataSource dataSource,
        ApplicationContext appCtx) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(appCtx.getResources("classpath:com/example/demo/mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
}
