package br.edu.infnet.contas.commands;

public class DepositarCommand extends BaseCommand<String> {

    public final double valor;

    public DepositarCommand(String id, double valor) {
        super(id);
        this.valor = valor;
    }
}
