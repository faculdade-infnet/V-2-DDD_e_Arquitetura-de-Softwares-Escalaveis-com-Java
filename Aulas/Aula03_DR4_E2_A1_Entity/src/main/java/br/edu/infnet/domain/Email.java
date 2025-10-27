package br.edu.infnet.domain;
import java.util.regex.Pattern;

public class Email {
    // Declaração do ValueObject
    private final String endereco;
    //--------------------------------------------------------------------------
    //RFC 5322
    private static final String EMAIL_REGEX =
            "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\." +
            "[a-zA-Z0-9_'^&/+-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    //--------------------------------------------------------------------------
    // Construtor
    public Email(String endereco) {
        if(endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio");
        }
        if(!EMAIL_PATTERN.matcher(endereco).matches()) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        this.endereco = endereco;
    }

    // Getters
    public String getEndereco() {
        return this.endereco;
    }
    
    public static Email of(String endereco) {
        return new Email(endereco);
    }

    // Metodo de comparacao do valor informado c/ o ultimo valor armazenado
    @Override
    public boolean equals(Object obj) {
        Email outro = (Email) obj;
        return this.endereco.equals(outro.getEndereco());
    }
}
