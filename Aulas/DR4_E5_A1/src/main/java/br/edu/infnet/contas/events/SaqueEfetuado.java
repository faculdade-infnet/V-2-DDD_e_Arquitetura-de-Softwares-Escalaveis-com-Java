package br.edu.infnet.contas.events;

public class SaqueEfetuado extends BaseEvent<String> {
    public final double valor;

    public SaqueEfetuado(String id, double valor) {
        super(id);
        this.valor = valor;
    }
}
