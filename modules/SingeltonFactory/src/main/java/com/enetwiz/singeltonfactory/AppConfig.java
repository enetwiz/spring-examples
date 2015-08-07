package com.enetwiz.singeltonfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
@ComponentScan("com.enetwiz.componentscope")
public class AppConfig {
    
    @Bean
    public ExampleSingelton exampleSingelton() {
        return ExampleSingelton.getInstance();
    }
    
}