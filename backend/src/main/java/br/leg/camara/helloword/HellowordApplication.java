package br.leg.camara.helloword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://start.spring.io/

@SpringBootApplication
//@RestController
public class HellowordApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellowordApplication.class, args);
	}

//	@GetMapping("/")
//	public String index() {
//		return "Olá Mundo!";
//	}
	
}
