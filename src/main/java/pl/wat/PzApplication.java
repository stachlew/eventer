package pl.wat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "pl.wat" })
public class PzApplication {

	public static void main(String[] args) {
		SpringApplication.run(PzApplication.class, args);
	}


}
