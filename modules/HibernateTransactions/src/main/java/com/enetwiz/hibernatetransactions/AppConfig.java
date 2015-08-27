package com.enetwiz.hibernatetransactions;

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



/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.enetwiz.hibernatetransactions")
public class AppConfig {
    
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
        properties.put("hibernate.show_sql",        "true");
        properties.put("hibernate.dialect",         "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.hbm2ddl.auto",    "create-drop"); //TODO: opisz w README.md
        
        return properties;
    }
    
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder( dataSource() );
        builder.scanPackages("com.enetwiz.hibernatetransactions")
               .addProperties( getHibernateProperties() );
        
        return builder.buildSessionFactory();
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager( SessionFactory pSessionFactory ) {
       return new HibernateTransactionManager( pSessionFactory );
    }
    
}