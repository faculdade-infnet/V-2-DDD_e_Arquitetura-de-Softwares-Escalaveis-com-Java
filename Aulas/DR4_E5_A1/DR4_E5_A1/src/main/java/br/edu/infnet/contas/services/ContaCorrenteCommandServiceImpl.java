package br.edu.infnet.contas.services;

import br.edu.infnet.contas.commands.CriarContaCommand;
import br.edu.infnet.contas.commands.DepositarCommand;
import br.edu.infnet.contas.commands.SacarCommand;
import br.edu.infnet.contas.domain.ContaCorrente;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteCommandServiceImpl implements ContaCorrenteCommandService {

    @Autowired
    private final CommandGateway commandGateway;

    public ContaCorrenteCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> abrirContaCorrente(ContaCorrente cc) {
        return commandGateway.send(new CriarContaCommand(
                UUID.randomUUID().toString(),
                cc.getTitular(),
                cc.getSaldo()));
    }

    @Override
    public CompletableFuture<String> depositar(String id, double valor) {
        return commandGateway.send(new DepositarCommand(id, valor));
    }

    @Override
    public CompletableFuture<String> sacar(String id, double valor) {
        return commandGateway.send(new SacarCommand(id, valor));
    }
}
