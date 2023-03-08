package fr.mlv.kafkajoin;

import fr.mlv.kafkajoin.models.Enfant;
import fr.mlv.kafkajoin.models.Parent;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Processor {

    @Value("${input.topic-1.name}")
    private String input_1;

    @Value("${input.topic-2.name}")
    private String input_2;

    @Value("${output.topic.name}")
    private String output;

    @Autowired
    public void process(StreamsBuilder builder) {

        final Serde<Parent> PARENT_SERDES = new JsonSerde<>(Parent.class);
        final Serde<Enfant> ENFANT_SERDES = new JsonSerde<>(Enfant.class);

        KStream<String, Parent> parentKStream =
                builder.stream(input_1, Consumed.with(Serdes.String(), PARENT_SERDES))
                        .peek((k, v) -> System.out.printf("-\nKey : %s Value: %s%n", k, v));

        ValueJoiner<Enfant, Parent, Enfant> valueJoiner =
                (enfant, parent) -> new Enfant(enfant.getId(), enfant.getName(), parent.getId(), parent.getName());

        builder.stream(input_2, Consumed.with(Serdes.String(), ENFANT_SERDES))
                .peek((k, v) -> System.out.printf("-\nKey : %s Value: %s%n", k, v))
                .join(
                        parentKStream,
                        valueJoiner,
                        JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(1)),
                        StreamJoined.with(Serdes.String(), ENFANT_SERDES, PARENT_SERDES))
                .peek((k, v) -> System.out.printf("%s dit: aggregated: %s%n", v.getName(), v.getParentName()))
                .peek((k, v) -> System.out.printf("Key : %s Value: %s%n", k, v))
                .to(output, Produced.with(Serdes.String(), ENFANT_SERDES));
    }
}
