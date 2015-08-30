package com.enetwiz.securitydatabaseauth;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("com.enetwiz.securitydatabaseauth")
public class AppConfig extends WebMvcConfigurerAdapter {
    
    // Template engine (JSP)
    @Bean
    public ViewResolver viewResolver() {
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        
        return viewResolver;
    }
    //EOF 
    
    // Database > Hibernate + MySQL config
    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // You can change bellow settings
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        
        return dataSource;
    }
    
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql",   "true");
        properties.put("hibernate.dialect",    "org.hibernate.dialect.MySQL5Dialect");
        
        return properties;
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder( dataSource() );
        builder.scanPackages("com.enetwiz.securitydatabaseauth")
               .addProperties( getHibernateProperties() );
        
        return builder.buildSessionFactory();
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager( SessionFactory pSessionFactory ) {
       return new HibernateTransactionManager( pSessionFactory );
    }
    // EOF Database > Hibernate + MySQL config
    
}