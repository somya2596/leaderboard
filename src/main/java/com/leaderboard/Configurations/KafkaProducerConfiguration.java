package com.leaderboard.Configurations;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.leaderboard.Model.ScoreModel;


@org.springframework.context.annotation.Configuration
public class KafkaProducerConfiguration {

	@Bean
	public ProducerFactory<String, ScoreModel> producerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}


	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("ENDGAME").partitions(1).replicas(1).build();
	}

	@Bean
	public KafkaTemplate<String, ScoreModel> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
}
