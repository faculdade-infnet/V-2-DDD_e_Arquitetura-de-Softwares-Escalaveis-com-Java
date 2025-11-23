package br.edu.infnet.contas.events;

import br.edu.infnet.contas.domain.Estado;

public class ContaCorrenteBloqueada extends BaseEvent<String> {

    public final Estado estado;

    public ContaCorrenteBloqueada(String id, Estado estado) {
        super(id);
        this.estado = estado;
    }
}
