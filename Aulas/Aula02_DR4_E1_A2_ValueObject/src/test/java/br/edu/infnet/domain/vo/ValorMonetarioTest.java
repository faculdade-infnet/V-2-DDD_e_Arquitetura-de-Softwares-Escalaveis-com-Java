package br.edu.infnet.domain.vo;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ValorMonetarioTest {

    // Teste Metodo de Adicao
    @Test
    public void testSomar() {
        System.out.println("Soma de valores monetários");
        ValorMonetario valor1 = new ValorMonetario(new BigDecimal(200.34));
        ValorMonetario valor2 = new ValorMonetario(new BigDecimal(100));
        ValorMonetario esperado = new ValorMonetario(new BigDecimal(300.34));
        ValorMonetario resultado = valor1.somar(valor2);
        assertEquals(esperado.getQuantia(), resultado.getQuantia());
    }

    // Teste Metodo de Subtracao
    @Test
    public void testSubtrair() {
        System.out.println("Subtrai valores monetários");
        ValorMonetario valor1 = new ValorMonetario(new BigDecimal(200.34));
        ValorMonetario valor2 = new ValorMonetario(new BigDecimal(100));
        ValorMonetario esperado = new ValorMonetario(new BigDecimal(100.34));
        ValorMonetario resultado = valor1.subtrair(valor2);
        assertEquals(esperado.getQuantia(), resultado.getQuantia());
    }
}
