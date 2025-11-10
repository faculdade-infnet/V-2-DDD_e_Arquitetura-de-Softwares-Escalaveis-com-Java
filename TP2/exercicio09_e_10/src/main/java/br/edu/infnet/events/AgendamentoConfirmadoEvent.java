package br.edu.infnet.events;

import br.edu.infnet.domain.Event;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

// Confirmação de um agendamento.
@Getter
public class AgendamentoConfirmadoEvent extends Event {

    private final UUID agendamentoId;
    private final UUID veterinarioId;
    private final LocalDateTime dataHora;

    public AgendamentoConfirmadoEvent(UUID agendamentoId, UUID veterinarioId, LocalDateTime dataHora) {
        super("AgendamentoConfirmado");
        this.agendamentoId = agendamentoId;
        this.veterinarioId = veterinarioId;
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " | Agendamento: %s | Veterinário: %s | Data: %s",
                agendamentoId, veterinarioId, dataHora
        );
    }
}