package br.edu.infnet.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoConfirmadoEvent (UUID agendamentoId, UUID veterinarioId, LocalDateTime dataHora) { }
