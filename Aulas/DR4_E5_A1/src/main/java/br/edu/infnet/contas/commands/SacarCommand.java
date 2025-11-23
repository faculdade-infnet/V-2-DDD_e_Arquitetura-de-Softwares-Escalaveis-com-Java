package br.edu.infnet.contas.commands;

public class SacarCommand extends BaseCommand<String> {

    public final double valor;

    public SacarCommand(String id, double valor) {
        super(id);
        this.valor = valor;
    }
}
