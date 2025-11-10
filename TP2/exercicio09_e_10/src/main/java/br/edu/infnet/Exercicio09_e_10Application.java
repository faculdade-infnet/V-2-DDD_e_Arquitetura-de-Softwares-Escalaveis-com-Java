package br.edu.infnet;

import br.edu.infnet.domain.Agendamento;
import br.edu.infnet.events.AgendamentoConfirmadoEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class Exercicio09_e_10Application {

    public static void main(String[] args) {
        SpringApplication.run(Exercicio09_e_10Application.class, args);
    }

    private void Teste(){
        Agendamento ag = new Agendamento(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now(),
                Agendamento.StatusAgendamento.PENDENTE
        );

        // Evento gerado
        AgendamentoConfirmadoEvent event = ag.confirmar();

        // Local para adicionar publicação do evento
        System.out.println(event);
    }
}
