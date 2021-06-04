package com.example.interactive.chat.config;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Artur Korra
 */
@Configuration
@EnableScheduling
@EnableTransactionManagement
@EnableSwagger2
@EnableCaching
@EnableJpaRepositories(basePackages = { "com.example.interactive.chat.repository" })
@ComponentScan(basePackages = { "com.example.interactive.chat" })
@SpringBootApplication
public class InteractiveChatApiConfigurer {

	protected static final Logger confLogger = LoggerFactory.getLogger(InteractiveChatApiConfigurer.class);

	@Bean
	public DataSource dataSource(Environment env) {
		confLogger.debug("------------> Initializing DataSource");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(env.getProperty("spring.datasource.url"));
		config.setUsername(env.getProperty("spring.datasource.username"));
		config.setPassword(env.getProperty("spring.datasource.password"));
		config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		config.addDataSourceProperty("implicitCachingEnabled", "true");
		config.addDataSourceProperty("fastConnectionFailoverEnabled", "true");
		return new HikariDataSource(config);

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource(env));
		em.setPackagesToScan("com.example.interactive.chat.model");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties(env));
		em.afterPropertiesSet();
		return em;
	}

	private Properties additionalProperties(Environment env) {
		Properties properties = new Properties();
		properties.setProperty("hibernate.jdbc.time_zone",
				env.getRequiredProperty("spring.jpa.properties.hibernate.jdbc.time_zone"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("x-recaptcha-token", "Origin", "Content-Type", "Accept",
				"Authorization", "Access-Control-Request-Method", "Access-Control-Request-Headers",
				"Access-Control-Allow-Origin"));
		configuration.addExposedHeader("Access-Control-Allow-Origin");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
}
