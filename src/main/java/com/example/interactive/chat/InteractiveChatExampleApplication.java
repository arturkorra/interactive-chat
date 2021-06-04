package com.example.interactive.chat;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Artur Korra
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
public class InteractiveChatExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InteractiveChatExampleApplication.class, args);
	}

	@Autowired
	private ApplicationContext appContext;

	@Override
	public void run(String... args) throws Exception {
		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
		}
	}

}
