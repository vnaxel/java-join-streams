package fr.mlv.kafkajoin.utils;

import fr.mlv.kafkajoin.models.Enfant;
import fr.mlv.kafkajoin.models.Parent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleLoader {

    @Value("${input.topic-1.name}")
    String parents;

    @Value("${input.topic-2.name}")
    String enfants;

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Enfant> templateEnfants, KafkaTemplate<String, Parent> templateParents) {
        return args -> {
            templateParents.send(parents, "1", new Parent(1, "Michel-Ryan"));
            templateParents.send(parents, "2", new Parent(2, "Jakeline"));
            templateParents.send(parents, "3", new Parent(3, "Jean-Bernadette"));
            templateEnfants.send(enfants, "1", new Enfant(4, "Arya"));
            templateEnfants.send(enfants, "2", new Enfant(5, "Jon"));
            templateEnfants.send(enfants, "3", new Enfant(6, "Mortimer"));
            templateEnfants.send(enfants, "1", new Enfant(7, "Mowgli"));
            templateEnfants.send(enfants, "2", new Enfant(8, "Oliver"));
            templateEnfants.send(enfants, "3", new Enfant(9, "Martine"));
            templateEnfants.send(enfants, "1", new Enfant(10, "Mathilde"));
            templateEnfants.send(enfants, "2", new Enfant(11, "Myriam"));
            templateEnfants.send(enfants, "3", new Enfant(12, "Ravi"));
        };
    }
}
