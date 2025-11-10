package br.edu.infnet.domain;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

// Abstração de um evento, os eventos concretos herdam esta classe.
@Getter
public abstract class Event {
    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final String eventType;

    protected Event(String eventType) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return String.format("[%s] Evento ID: %s | Data: %s",
                eventType, eventId, occurredOn);
    }
}
