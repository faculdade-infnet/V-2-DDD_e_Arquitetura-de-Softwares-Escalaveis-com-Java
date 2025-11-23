package br.edu.infnet.contas.events;

import br.edu.infnet.contas.domain.Estado;

public class ContaCorrenteAtivada extends BaseEvent<String> {

    public final Estado estado;

    public ContaCorrenteAtivada(String id, Estado estado) {
        super(id);
        this.estado = estado;
    }
}
