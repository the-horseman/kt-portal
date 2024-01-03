package com.madeira.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class DbConfig {
    
    @Value("${spring.datasource.driverClassName}")
    private String datasourceDriverClassName; 
    
    @Value("${spring.datasource.url}")
    private String datasourceUrl; 
        
    @Value("${spring.datasource.username}")
    private String datasourceUsername; 
    
    @Value("${spring.datasource.password}")
    private String datasourcePassword; 
     
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect; 
    
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateDdlAuto; 
    
    @Value("${spring.jpa.show-sql}")
    private String jpaShowSql; 

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String hibernateFormatSql; 

}
