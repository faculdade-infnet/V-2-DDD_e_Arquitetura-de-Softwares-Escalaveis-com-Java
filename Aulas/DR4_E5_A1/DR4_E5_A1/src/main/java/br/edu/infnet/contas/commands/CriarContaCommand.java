package br.edu.infnet.contas.commands;

public class CriarContaCommand extends BaseCommand<String> {
    
    public final String titular;
    public final double saldo;

    public CriarContaCommand(String id, String titular, double depositoInicial) {
        super(id);
        this.titular = titular;
        this.saldo = depositoInicial;
    }
}
