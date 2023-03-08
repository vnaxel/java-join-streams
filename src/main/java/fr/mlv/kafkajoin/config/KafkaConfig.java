package fr.mlv.kafkajoin.config;

import fr.mlv.kafkajoin.models.Enfant;
import fr.mlv.kafkajoin.models.Parent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic parents() {
        return TopicBuilder.name("topic-1").partitions(2).replicas(1).build();
    }

    @Bean
    NewTopic enfants() {
        return TopicBuilder.name("topic-2").partitions(2).replicas(1).build();
    }

    @Bean
    NewTopic enfantsContents() {
        return TopicBuilder.name("topic-3").partitions(2).replicas(1).build();
    }


}
