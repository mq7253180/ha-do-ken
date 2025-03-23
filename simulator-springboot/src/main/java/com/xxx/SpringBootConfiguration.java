package com.xxx;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.session.web.http.DefaultCookieSerializer;

@PropertySource(value = {"classpath:application-sensitiveness.properties"})
@Configuration
public class SpringBootConfiguration {
	@Autowired
    private DefaultCookieSerializer cookieSerializer;

    @PostConstruct
    public void init() {
    	cookieSerializer.setUseHttpOnlyCookie(false);
    }
}