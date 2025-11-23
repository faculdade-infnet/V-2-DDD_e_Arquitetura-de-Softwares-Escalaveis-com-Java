package br.edu.infnet.contas.events;

public class DepositoEfetuado extends BaseEvent<String> {
    public final double valor;

    public DepositoEfetuado(String id, double valor) {
        super(id);
        this.valor = valor;
    }
}
