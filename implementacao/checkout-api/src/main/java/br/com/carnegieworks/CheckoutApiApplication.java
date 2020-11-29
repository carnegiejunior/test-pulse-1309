package br.com.carnegieworks;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//AnnotationConfigWebApplicationContext#scan("com.mypackages.config")
public class CheckoutApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(CheckoutApiApplication.class, args);
	}

}
