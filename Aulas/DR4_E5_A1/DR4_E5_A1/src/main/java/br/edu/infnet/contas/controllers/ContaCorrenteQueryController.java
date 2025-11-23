package br.edu.infnet.contas.controllers;

import br.edu.infnet.contas.domain.ContaCorrente;
import br.edu.infnet.contas.services.ContaCorrenteQueryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contas")
public class ContaCorrenteQueryController {
    
    private final ContaCorrenteQueryService service;

    public ContaCorrenteQueryController(ContaCorrenteQueryService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}/eventos")
    public List<Object> listarEventos(@PathVariable(value = "id") String id){ 
        return service.listarEventos(id);
    }
    
    @GetMapping("/{id}")
    public ContaCorrente obterPorId(@PathVariable(value = "id") String id){ 
        return service.obterPorId(id).get();
    }
}
