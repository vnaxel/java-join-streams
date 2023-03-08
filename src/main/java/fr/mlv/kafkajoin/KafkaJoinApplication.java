package fr.mlv.kafkajoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class KafkaJoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaJoinApplication.class, args);
	}

}
