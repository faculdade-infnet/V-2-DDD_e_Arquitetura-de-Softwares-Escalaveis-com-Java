package br.edu.infnet.contas.domain;

import br.edu.infnet.contas.commands.CriarContaCommand;
import br.edu.infnet.contas.commands.DepositarCommand;
import br.edu.infnet.contas.commands.SacarCommand;
import br.edu.infnet.contas.events.ContaCorrenteAtivada;
import br.edu.infnet.contas.events.ContaCorrenteBloqueada;
import br.edu.infnet.contas.events.ContaCorrenteCriada;
import br.edu.infnet.contas.events.DepositoEfetuado;
import br.edu.infnet.contas.events.SaqueEfetuado;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Entity
public class ContaCorrente {

    @AggregateIdentifier
    @Id
    private String id;
    private String titular;
    private double saldo;
    private String estado;

    public ContaCorrente() {
    }

    //--------------------------------------------------------------------------
    @CommandHandler
    public ContaCorrente(CriarContaCommand comando) {
        AggregateLifecycle.apply(new ContaCorrenteCriada(comando.id, comando.titular, comando.saldo));
    }

    @EventSourcingHandler
    protected void on(ContaCorrenteCriada evento) {
        this.id = evento.id;
        this.titular = evento.titular;
        this.saldo = evento.saldo;
        this.estado = String.valueOf(Estado.CRIADA);

        AggregateLifecycle.apply(new ContaCorrenteAtivada(this.id, Estado.ATIVADA));
    }

    @EventSourcingHandler
    protected void on(ContaCorrenteAtivada evento) {
        this.estado = String.valueOf(evento.estado);
    }
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @CommandHandler
    protected void on(DepositarCommand depositarCommand) {
        AggregateLifecycle.apply(new DepositoEfetuado(depositarCommand.id, depositarCommand.valor));
    }

    @EventSourcingHandler
    protected void on(DepositoEfetuado depositoEfetuado) {

        if (this.saldo < 0 & (this.saldo + depositoEfetuado.valor) >= 0) {
            AggregateLifecycle.apply(new ContaCorrenteAtivada(this.id, Estado.ATIVADA));
        }

        this.saldo += depositoEfetuado.valor;
    }
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @CommandHandler
    protected void on(SacarCommand sacarCommand) {
        AggregateLifecycle.apply(new SaqueEfetuado(sacarCommand.id, sacarCommand.valor));
    }

    @EventSourcingHandler
    protected void on(SaqueEfetuado saqueEfetuado) {

        if (this.saldo >= 0 & (this.saldo - saqueEfetuado.valor) < 0) {
            AggregateLifecycle.apply(new ContaCorrenteBloqueada(this.id, Estado.BLOQUEADA));
        }
        this.saldo -= saqueEfetuado.valor;
    }

    @EventSourcingHandler
    protected void on(ContaCorrenteBloqueada contaCorrenteBloqueada) {
        this.estado = String.valueOf(contaCorrenteBloqueada.estado);
    }
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
