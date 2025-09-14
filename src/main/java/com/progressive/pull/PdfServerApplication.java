package com.progressive.pull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.progressive.pull")
public class PdfServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfServerApplication.class, args);
	}

}
