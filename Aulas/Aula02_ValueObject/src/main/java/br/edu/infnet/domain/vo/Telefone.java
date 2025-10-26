package br.edu.infnet.domain.vo;

public class Telefone {
    // Declaração do ValueObject
    private int ddd;
    private int numero;
    private TipoTelefone tipo;

    // Construtor
    private Telefone() {}

    // Getters
    public int getDdd() {
        return ddd;
    }
    public int getNumero() {
        return numero;
    }
    public TipoTelefone getTipo() {
        return tipo;
    }
    
    public enum TipoTelefone {
        RESIDENCIAL, CELULAR, COMERCIAL
    }

    // Metodo com condicoes de comparacao
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefone outro = (Telefone) obj;
        if (this.ddd != outro.ddd) {
            return false;
        }
        if (this.numero != outro.numero) {
            return false;
        }
        if (this.tipo != outro.tipo) {
            return false;
        }
        return true;
    }

    // Design Pattern Builder
    public static class Builder {
        private final Telefone telefone = new Telefone();
        
        public Builder addDdd(int ddd) {
            if (ddd <= 0) {
                throw new IllegalArgumentException("DDD inválido");
            }
            telefone.ddd = ddd;
            return this;
        }

        public Builder addNumero(int numero) {
            if (numero <= 0) {
                throw new IllegalArgumentException("Número inválido");
            }
            telefone.numero = numero;
            return this;
        }

        public Builder addTipoTelefone(TipoTelefone tipoTelefone) {
            telefone.tipo = tipoTelefone;
            return this;
        }
        
        public Telefone build() {
            return this.telefone;
        }
    }
}
