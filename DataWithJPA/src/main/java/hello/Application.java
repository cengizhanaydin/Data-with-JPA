package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);

	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {

			// çeşitli müşterileri kaydediyoruz.
			repository.save(new Customer("Cengizhan", "AYDIN"));
			repository.save(new Customer("Hayrettin", "AYDIN"));
			repository.save(new Customer("Bedirhan", "AYDIN"));
			repository.save(new Customer("Nermin", "AYDIN"));

			// tüm müşterileri getirmek
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// idsine göre getiriyor.
			repository.findById(1L).ifPresent(customer -> {
				log.info("Customer found with findById(1L):");
				log.info("---------------------------------");
				log.info(customer.toString());
				log.info("");
			});
			
			
			//
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("AYDIN").forEach(aydın -> {
				log.info(aydın.toString());
			});
			log.info("");

		};
	}

}
