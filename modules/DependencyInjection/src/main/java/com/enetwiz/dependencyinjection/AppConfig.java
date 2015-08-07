package com.enetwiz.dependencyinjection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
@ComponentScan("com.enetwiz.dependencyinjection")
public class AppConfig {
    
    @Bean
    @Scope("prototype")
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }
    
}