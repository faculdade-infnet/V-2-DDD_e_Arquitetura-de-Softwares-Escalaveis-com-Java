package br.edu.infnet.events;

public class PublisherEvent {
    // Envento para a publicação para o sistema de mensageria
    public static void publish(Object event) {
        System.out.println("📨 Evento publicado: " + event.toString());
    }
}
