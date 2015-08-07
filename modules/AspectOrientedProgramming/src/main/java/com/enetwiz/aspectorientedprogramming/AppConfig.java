package com.enetwiz.aspectorientedprogramming;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
@ComponentScan("com.enetwiz.aspectorientedprogramming")
@EnableAspectJAutoProxy
//TODO: aby wlaczyc ww anotacje potrzebna jest biblioteka aspectjweaver
public class AppConfig {
    
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
    
}