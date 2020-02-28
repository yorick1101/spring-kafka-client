package me.yorick.poc.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"me.yorick.spring.mod","me.yorick.poc.kafka"})
public class KafkaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaClientApplication.class, args);
	}

}
