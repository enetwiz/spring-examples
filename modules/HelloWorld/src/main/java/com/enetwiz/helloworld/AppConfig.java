package com.enetwiz.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
// UWAGA: uzywanie konfiguracji przez anotacje wymaga zainstalowania dodatkowej zaleznosci o nazwie: cglib
@Configuration
@ComponentScan("com.enetwiz.helloworld")
public class AppConfig {
    
    @Bean
    public HelloBean helloBean() {
        return new HelloBean();
    }
    
}