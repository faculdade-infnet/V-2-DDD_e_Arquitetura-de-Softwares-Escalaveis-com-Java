package br.edu.infnet.contas.events;

public class ContaCorrenteCriada extends BaseEvent<String> {

    public final String titular;
    public final double saldo;

    public ContaCorrenteCriada(String id, String titular, double depositoInicial) {
        super(id);
        this.titular = titular;
        this.saldo = depositoInicial;
    }
}
