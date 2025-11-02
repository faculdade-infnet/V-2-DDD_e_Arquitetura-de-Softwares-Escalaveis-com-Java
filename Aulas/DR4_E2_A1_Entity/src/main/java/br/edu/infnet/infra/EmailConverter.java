package br.edu.infnet.infra;

import br.edu.infnet.domain.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Conversor JPA — ou seja, ela diz ao Hibernate como transformar o seu Value Object (Email)
// em algo que o banco de dados entende (String, etc)
@Converter(autoApply=true)
public class EmailConverter  implements AttributeConverter<Email, String> {

    // Chamado quando JPA vai salvar a entidade no banco.
    // Ele pega o objeto Email e retorna a representação em String (o endereço de e-mail).
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email.getEndereco();
    }

    // Chamado quando JPA lê do banco.
    // Ele pega a String da coluna ("samuel@gmail.com") e a transforma novamente em um objeto Email do domínio.
    @Override
    public Email convertToEntityAttribute(String email) {
        return new Email(email);
    }
}
