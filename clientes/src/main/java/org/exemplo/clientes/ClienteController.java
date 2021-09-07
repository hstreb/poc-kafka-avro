package org.exemplo.clientes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

    private KafkaTemplate<String, Cliente> kafkaTemplate;
    private String topico;

    public ClienteController(KafkaTemplate<String, Cliente> kafkaTemplate,
                             @Value("${topic.clientes.name:clientes}") String topico) {
        this.kafkaTemplate = kafkaTemplate;
        this.topico = topico;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(@RequestBody ClienteRequest clienteRequest) {
        var cliente = Cliente.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setNome(clienteRequest.nome())
                .setDocumento(clienteRequest.documento())
                .build();
        LOG.debug("Enviando cliente {} com chave {}", cliente, cliente.getDocumento());
        kafkaTemplate.send(topico, cliente.getDocumento(), cliente);
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getDocumento());
    }

    record ClienteRequest(String nome, String documento) {
    }

    record ClienteResponse(String id, String nome, String documento) {
    }
}