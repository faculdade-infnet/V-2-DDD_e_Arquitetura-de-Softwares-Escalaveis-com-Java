package br.edu.infnet.pedidos.infra;

import br.edu.infnet.pedidos.domain.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.google.cloud.spring.pubsub.support.converter.ConvertedBasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class PedidoMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoMessageService.class);

    @Bean
    public JacksonPubSubMessageConverter jacksonPubSubMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Pedido.class, new PedidoSerializer());
        simpleModule.addDeserializer(Pedido.class, new PedidoDeserializer());
        objectMapper.registerModule(simpleModule);
        return new JacksonPubSubMessageConverter(objectMapper);
    }

    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(
            @Qualifier("inputMessageChannel") MessageChannel messageChannel, PubSubTemplate pubSubTemplate) {

        pubSubTemplate.setMessageConverter(jacksonPubSubMessageConverter());
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "dr4_assinatura");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        //adapter.setPayloadType(String.class);
        adapter.setPayloadType(Pedido.class);
        return adapter;
    }

    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(Pedido payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) ConvertedBasicAcknowledgeablePubsubMessage<Pedido> message) {

        LOG.info("***** Mensagem Recebida ---> " + payload);
        message.ack();
    }
}





//    @ServiceActivator(inputChannel = "inputMessageChannel")
//    public void messageReceiver(Pedido payload,
//            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
//
//        LOG.info("***** Mensagem Recebida ---> " + payload);
//        message.ack();
//    }