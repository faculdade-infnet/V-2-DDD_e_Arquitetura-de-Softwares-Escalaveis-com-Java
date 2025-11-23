package br.edu.infnet.contas.controllers;

import br.edu.infnet.contas.domain.ContaCorrente;
import br.edu.infnet.contas.services.ContaCorrenteCommandService;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contas")
public class ContaCorrenteCommandController {

    private final ContaCorrenteCommandService service;

    public ContaCorrenteCommandController(ContaCorrenteCommandService service) {
        this.service = service;
    }

    @PostMapping
    public CompletableFuture<String> abrirContaCorrente(@RequestBody ContaCorrente cc) {
        return service.abrirContaCorrente(cc);
    }
    
     @PutMapping(value = "/depositar")
    public CompletableFuture<String> depositar(@RequestBody Map<String, Object> deposito){
        return service.depositar((String) deposito.get("id"), (Double) deposito.get("valor"));
    }

    @PutMapping(value = "/sacar")
    public CompletableFuture<String> sacar(@RequestBody Map<String, Object> saque){
        return service.sacar((String) saque.get("id"), (Double) saque.get("valor"));
    }
}
