package br.edu.infnet.service;

import br.edu.infnet.domain.Agendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public class AgendamentoService {
    public void processarAgendamento() {
        UUID id = UUID.randomUUID();
        UUID veterinarioId = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();

        Agendamento ag = new Agendamento(
                id,
                veterinarioId,
                clienteId,
                LocalDateTime.now(),
                "Consulta",
                Agendamento.StatusAgendamento.CONFIRMADO);

        // Dispara evento
        ag.confirmar();
    }
}
