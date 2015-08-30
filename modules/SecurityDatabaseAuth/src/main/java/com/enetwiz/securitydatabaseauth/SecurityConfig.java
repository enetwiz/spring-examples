package com.enetwiz.securitydatabaseauth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity pHttp) throws Exception {
        
        pHttp.authorizeRequests()
                .antMatchers( "/" ).permitAll()
                .antMatchers( "/login**" ).permitAll()
                .antMatchers( "/admin/**" ).hasRole( "ADMIN" )
                .anyRequest().authenticated()
                .and();
        
        pHttp.formLogin()
                .loginPage( "/login" )
                .loginProcessingUrl( "/login.do" )
                .defaultSuccessUrl( "/" )
                .failureUrl( "/login?err=1" )
                .usernameParameter( "username" )
                .passwordParameter( "password" )
                .and();
        
        pHttp.logout()
                .logoutRequestMatcher( new AntPathRequestMatcher( "/logout" ) )
                .logoutSuccessUrl( "/login?out=1" )
                .deleteCookies( "JSESSIONID" )
                .invalidateHttpSession( true )
                .and();
        
    }
    
}