package com.enetwiz.helloworld;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
// UWAGA: uzywanie konfiguracji przez anotacje wymaga zainstalowania dodatkowej zaleznosci o nazwie: cglib
@Configuration
public class AppConfig {
    
    @Bean
    public Hello hello() {
        return new Hello();
    }
    
}