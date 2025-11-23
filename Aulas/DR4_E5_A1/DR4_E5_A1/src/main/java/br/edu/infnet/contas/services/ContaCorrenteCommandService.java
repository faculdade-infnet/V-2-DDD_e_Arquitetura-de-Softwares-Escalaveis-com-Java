package br.edu.infnet.contas.services;

import br.edu.infnet.contas.domain.ContaCorrente;
import java.util.concurrent.CompletableFuture;

public interface ContaCorrenteCommandService {
    public CompletableFuture<String> abrirContaCorrente(ContaCorrente cc);
    public CompletableFuture<String> depositar(String id, double valor);
    public CompletableFuture<String> sacar(String id, double valor);
}
