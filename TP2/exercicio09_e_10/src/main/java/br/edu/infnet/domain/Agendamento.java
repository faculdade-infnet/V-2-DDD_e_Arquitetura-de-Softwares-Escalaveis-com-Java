package br.edu.infnet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import br.edu.infnet.events.AgendamentoConfirmadoEvent;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Agendamento {

    private final UUID id;
    private final UUID veterinarioId;
    private LocalDateTime dataHora;
    private StatusAgendamento status;

    public enum StatusAgendamento {
        PENDENTE, CONFIRMADO, CANCELADO
    }

    // Com a confirmação, cria-se e retorna um evento de domínio
    public AgendamentoConfirmadoEvent  confirmar() {
        this.status = StatusAgendamento.CONFIRMADO;

        return new AgendamentoConfirmadoEvent(id, veterinarioId, dataHora);
    }
}

