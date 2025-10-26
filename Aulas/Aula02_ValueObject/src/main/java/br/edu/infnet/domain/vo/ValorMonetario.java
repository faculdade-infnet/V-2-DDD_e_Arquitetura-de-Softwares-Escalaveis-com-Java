package br.edu.infnet.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ValorMonetario {
    // Declaração do ValueObject
    private final BigDecimal quantia;

    // Construtor
    public ValorMonetario(BigDecimal quantia) {
        if(quantia == null || quantia.signum() < 0) {
            throw new IllegalArgumentException("Valor Monetário não pode ser nulo ou negativo");
        }
        this.quantia = quantia.setScale(2, RoundingMode.HALF_UP);        
    }

    // Getter
    public BigDecimal getQuantia() {
        return this.quantia;
    }

    // Metodo de Soma
    public ValorMonetario somar(ValorMonetario outro) {
        if(outro == null) {
            throw new IllegalArgumentException("Valor Monetário não pode ser nulo ou negativo");
        }
        return new ValorMonetario(this.quantia.add(outro.getQuantia()));
    }

    // Metodo de Subtracao
    public ValorMonetario subtrair(ValorMonetario outro) {
        if(outro == null) {
            throw new IllegalArgumentException("Valor Monetário não pode ser nulo ou negativo");
        }
        return new ValorMonetario(this.quantia.subtract(outro.getQuantia()));
    }

    // Metodo de comparacao do valor informado c/ o ultimo valor armazenado
    @Override
    public boolean equals(Object obj) {
        final ValorMonetario outro = (ValorMonetario) obj;
        return Objects.equals(this.quantia, outro.getQuantia());
    }
}
