package org.exemplo.clientes;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

	@Value("${topic.clientes.name:clientes}")
	private String topico;

	@Value("${topic.clientes.partitions:3}")
	private Integer particoes;

	@Value("${topic.clientes.replication:1}")
	private short replicas;

	@Bean
	NewTopic topicoClientes() {
		return new NewTopic(topico, particoes, replicas);
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}