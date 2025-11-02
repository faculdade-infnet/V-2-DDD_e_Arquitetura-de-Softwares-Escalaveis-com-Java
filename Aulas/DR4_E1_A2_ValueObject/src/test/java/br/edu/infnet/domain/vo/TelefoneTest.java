package br.edu.infnet.domain.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TelefoneTest {
    @Test
    public void testTelefone() {
        System.out.println("Teste de Construção de Telefone");
        Telefone tel1 = new Telefone.Builder()
                .addDdd(21)
                .addNumero(21228800)
                .addTipoTelefone(Telefone.TipoTelefone.COMERCIAL)
                .build();
        Telefone tel2 = new Telefone.Builder()
                .addDdd(21)
                .addNumero(21228800)
                .addTipoTelefone(Telefone.TipoTelefone.COMERCIAL)
                .build();
        assertEquals(tel1, tel2);
    }
}
