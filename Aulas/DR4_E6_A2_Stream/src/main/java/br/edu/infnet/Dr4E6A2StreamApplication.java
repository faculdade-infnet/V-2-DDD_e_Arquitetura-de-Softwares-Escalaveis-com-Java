package br.edu.infnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;

@SpringBootApplication
public class Dr4E6A2StreamApplication implements CommandLineRunner {
    
    private static final Logger LOG = LoggerFactory.getLogger(Dr4E6A2StreamApplication.class);

    @Autowired
    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(Dr4E6A2StreamApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String mensagem = "Somente uma mensagem";
        LOG.info("Enviando uma mensagem: " + mensagem);
        streamBridge.send("enviarMensagem-out-0", mensagem);
    }
}
