package com.processamentotweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProcessamentoTweetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessamentoTweetsApplication.class, args);
	}
}
