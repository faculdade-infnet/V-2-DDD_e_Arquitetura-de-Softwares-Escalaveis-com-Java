package br.edu.infnet;

import br.edu.infnet.domain.Cliente;
import br.edu.infnet.domain.Email;
import br.edu.infnet.domain.Telefone;
import br.edu.infnet.infra.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Dr4E2A1EntityApplication implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(Dr4E2A1EntityApplication.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(Dr4E2A1EntityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testaCliente();
    }

    public void testaCliente() {
        LOG.info("testes de Persistência");
        LOG.info("--- Inclusão ---");
        //Cliente cliente = new  Cliente("Machado de Assis", "machado@abl.org.br", "(21) 21228800");
        //clienteRepository.save(cliente);
        // ------------------------------------------------------------------------------------------------------------

        Email email = new Email("rechel@gmail.com");
        Telefone fone = new Telefone.Builder()
                .addDdd(21)
                .addNumero(21288800)
                .addTipoTelefone(Telefone.TipoTelefone.COMERCIAL)
                .build();
        //Cliente reachel = new Cliente("reachel", email, fone);
        //clienteRepository.save(reachel);

        // ------------------------------------------------------------------------------------------------------------
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cli : clientes) {
            LOG.info(cli.getNome() + " - " + cli.getEmail() + " - " + cli.getFone());
        }
    }
}
