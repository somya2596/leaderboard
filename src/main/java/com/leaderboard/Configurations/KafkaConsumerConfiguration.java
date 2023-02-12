package com.leaderboard.Configurations;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.leaderboard.Model.ScoreModel;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    
	@Bean
	public ConsumerFactory<String, ScoreModel> scoreConsumer() {

		// HashMap to store the configurations
		Map<String, Object> map = new HashMap<>();

		// put the host IP in the map
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		// put the group ID of consumer in the map
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "id");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		map.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
		map.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
		map.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.leaderboard.Entity.Score");
		map.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example");

		// return message in JSON formate
		return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(),
				new JsonDeserializer<>(ScoreModel.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ScoreModel> scoreListner() {
		ConcurrentKafkaListenerContainerFactory<String, ScoreModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(scoreConsumer());
		return factory;
	}

}
