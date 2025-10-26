package br.edu.infnet.domain.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {


    @Test
    public void testEquals() {
        Email valor1 = new Email("armenio.cardoso@prof.infnet.edu.br");
        Email valor2 = new Email("armenio.cardoso@prof.infnet.edu.br");
        System.out.println("Teste de Igualdade de Emails");
        assertEquals(true, valor1.equals(valor2));
    }
    
    @Test
    public void testErro() {
        System.out.println("Teste de Email Inválido");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           Email valor1 = new Email("armenio.cardoso@teste"); 
        });
        String mensagemEsperada = "E-mail inválido";
        String mensagemDisparada = exception.getMessage();
        
        assertTrue(mensagemDisparada.contains(mensagemEsperada));
    }
}
