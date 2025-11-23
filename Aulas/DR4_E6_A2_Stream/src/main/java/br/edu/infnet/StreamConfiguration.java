package br.edu.infnet;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@Configuration
public class StreamConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(StreamConfiguration.class);

    @Bean
    public Consumer<Message<String>> receberMensagem() {
        return mensagem -> {
            LOG.info("receberMensagem: " + mensagem.getPayload());
        };
    }

//    @Bean
//    public Supplier<Message<String>> enviarMensagem() {
//        return () -> {
//            Message<String> mensagem = MessageBuilder.withPayload("mensagem-" + Math.random()).build();
//            LOG.info("enviarMensagem: " + mensagem.getPayload());
//            return mensagem;
//        };
//    }
}
