package br.edu.infnet.infra;

import br.edu.infnet.domain.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

// Classe de acesso ao tópico do Google Cloud
@Service
public class PedidoMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoMessageService.class);

    // Configura o conversor para Pedido
    @Bean
    public JacksonPubSubMessageConverter jacksonPubSubMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Pedido.class, new PedidoSerializer());
        simpleModule.addDeserializer(Pedido.class, new PedidoDeserializer());
        objectMapper.registerModule(simpleModule);
        return new JacksonPubSubMessageConverter(objectMapper);
    }

    // Canal de mensagens para receber mensagens
    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    // Adaptador de entrada (inbound) do Pub/Sub
    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(
            @Qualifier("inputMessageChannel") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {

        pubSubTemplate.setMessageConverter(jacksonPubSubMessageConverter());
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "dr4_topic-sub");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(Pedido.class); // Garante que o payload seja Pedido
        return adapter;
    }

    // Recebe a mensagem do canal
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(
            @Payload Pedido payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<Pedido> message) {

        LOG.info("***** Mensagem Recebida ---> {}", payload);
        message.ack();
    }

    // Método auxiliar para publicar mensagens
    public void enviarPedido(Pedido pedido, PubSubTemplate pubSubTemplate) {
        pubSubTemplate.setMessageConverter(jacksonPubSubMessageConverter());
        pubSubTemplate.publish("dr4_topic-sub", pedido);
        LOG.info("***** Mensagem Enviada ---> {}", pedido);
    }
}