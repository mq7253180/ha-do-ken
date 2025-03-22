package com.xxx;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:application-sensitiveness.properties"})
@Configuration
public class SpringBootConfiguration {
    @PostConstruct
    public void init() {
    	
    }
}