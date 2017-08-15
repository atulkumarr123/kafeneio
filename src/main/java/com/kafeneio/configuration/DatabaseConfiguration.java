package com.kafeneio.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.kafeneio.repository")
//@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@PropertySource(value ={"classpath:properties/application.properties"})
public class DatabaseConfiguration {
@Autowired
private Environment environment;

@Bean
public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
    return dataSource;
}

private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    return properties;        
}


@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
  LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
  em.setDataSource(dataSource());
  em.setPackagesToScan(new String[] { "com.kafeneio.model" });

  JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
  em.setJpaVendorAdapter(vendorAdapter);
  em.setJpaProperties(hibernateProperties());

  return em;
}


@Bean
public JpaTransactionManager transactionManager() {
  JpaTransactionManager transactionManager = new JpaTransactionManager();

  return transactionManager;
}

}
