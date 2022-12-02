package com.jcohy.yilin;

import com.jcohy.yilin.model.Customer;
import com.jcohy.yilin.model.CustomerRepository;
import io.r2dbc.spi.ConnectionFactory;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
@RestController
@EnableR2dbcRepositories
public class YiLinApplication {

	private static final Logger log = LoggerFactory.getLogger(YiLinApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(YiLinApplication.class, args);
	}

	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}

//	@Bean
//	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//		initializer.setConnectionFactory(connectionFactory);
//		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//		return initializer;
//	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {

		return (args) -> {
			// save a few customers
			repository.saveAll(Arrays.asList(new Customer(null,"Jack", "Bauer"),
							new Customer(null,"Chloe", "O'Brian"),
							new Customer(null,"Kim", "Bauer"),
							new Customer(null,"David", "Palmer"),
							new Customer(null,"Michelle", "Dessler")))
					.blockLast(Duration.ofSeconds(10));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().doOnNext(customer -> {
				log.info(customer.toString());
			}).blockLast(Duration.ofSeconds(10));

			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L).doOnNext(customer -> {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			}).block(Duration.ofSeconds(10));


			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").doOnNext(bauer -> {
				log.info(bauer.toString());
			}).blockLast(Duration.ofSeconds(10));;
			log.info("");
		};
	}
}




