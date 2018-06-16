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
	public CommandLineRunner demo (CustomerRepository customerRepository) {
		return (args) ->{
			customerRepository.save(new Customer("Cengizhan","AYDIN"));
			customerRepository.save(new Customer("Hayrettin","AYDIN"));
			customerRepository.save(new Customer("Bedirhan","AYDIN"));
			customerRepository.save(new Customer("Nermin","AYDIN"));
			
			log.info("Tüm müşteriler sıralanıyor.");
			log.info("---------------------------");
			for(Customer customer : customerRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
			log.info("1l li müşteri sıralanıyor.");
			log.info("--------------------------");
			customerRepository.findById(1L).ifPresent(customer -> {
				log.info(customer.toString());
			});
			log.info("");
			
			log.info("Soyadına göre müşteriler sıralanıyor.");
			log.info("-------------------------------------");
			customerRepository.findByLastName("AYDIN").forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");
	
		};
	}
}
