package com.enetwiz.helloworldvaadin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
@ComponentScan("com.enetwiz.helloworldvaadin")
public class AppConfig {
    
    @Bean
    public VaadinUIProvider vaadinUIProvider() {
        return new VaadinUIProvider();
    }
    
}