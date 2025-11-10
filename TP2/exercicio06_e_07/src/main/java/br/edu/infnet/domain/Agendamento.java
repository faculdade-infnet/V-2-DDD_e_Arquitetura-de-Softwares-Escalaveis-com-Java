package br.edu.infnet.domain;

import br.edu.infnet.events.AgendamentoConfirmadoEvent;
import br.edu.infnet.events.PublisherEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Agendamento {

    private final UUID id;
    private final UUID veterinarioId;
    private final UUID clienteId;
    private LocalDateTime dataHora;
    private String tipoServico;                 // Ex: "Consulta", "Banho", "Tosa"
    private StatusAgendamento status;

    public enum StatusAgendamento {
        PENDENTE, CONFIRMADO, CANCELADO
    }

    // Regra de negócio (invariante): não confirma ação sem veterinário
    public void confirmar() {
        if (veterinarioId == null) {
            throw new IllegalStateException("Agendamento deve ter um veterinário associado antes de confirmar.");
        }

        this.status = StatusAgendamento.CONFIRMADO;

        var evento = new AgendamentoConfirmadoEvent(this.id, this.veterinarioId, this.dataHora);
        PublisherEvent.publish(evento);
    }
}

