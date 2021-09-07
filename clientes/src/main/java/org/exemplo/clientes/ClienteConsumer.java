package org.exemplo.clientes;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteConsumer.class);

    @KafkaListener(topics = "${topic.clientes.name:clientes}", groupId = "cliente")
    public void consume(ConsumerRecord<String, Cliente> consumerRecord) {
        Cliente cliente = consumerRecord.value();
        LOG.debug("Recebendo cliente {} com chave {} e offset {} na partição {}",
                cliente,
                consumerRecord.key(),
                consumerRecord.offset(),
                consumerRecord.partition());
    }
}
