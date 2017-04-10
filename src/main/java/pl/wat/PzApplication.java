package pl.wat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.wat.logic.CustomerService;


@SpringBootApplication(scanBasePackages = { "pl.wat" })
public class PzApplication {

	public static void main(String[] args) {
		SpringApplication.run(PzApplication.class, args);
		System.out.println("API DOSTEPNE POD: Localhost:8080");
	}


}
