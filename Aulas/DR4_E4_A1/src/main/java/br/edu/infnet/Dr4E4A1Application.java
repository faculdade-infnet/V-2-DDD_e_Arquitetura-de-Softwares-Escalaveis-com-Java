package br.edu.infnet;

import br.edu.infnet.pedidos.domain.Pedido;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dr4E4A1Application implements CommandLineRunner {
    
    private static Logger LOG = LoggerFactory.getLogger(Dr4E4A1Application.class);
    
    @Autowired
    private PubSubTemplate pubSubTemplate;
    @Autowired
    private JacksonPubSubMessageConverter converter;

    public static void main(String[] args) {
        SpringApplication.run(Dr4E4A1Application.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        //String mensagem = "Hello DR4 World!";
        //pubSubTemplate.publish("dr4_topic", mensagem);
        //LOG.info("***** Mensagem Publicada ---> " + mensagem);
        Pedido pedido = new Pedido(1234L, 4321L);
        pubSubTemplate.setMessageConverter(converter);
        pubSubTemplate.publish("dr4_topic", pedido);
        LOG.info("***** Mensagem Publicada ---> " + pedido);
    }
}
