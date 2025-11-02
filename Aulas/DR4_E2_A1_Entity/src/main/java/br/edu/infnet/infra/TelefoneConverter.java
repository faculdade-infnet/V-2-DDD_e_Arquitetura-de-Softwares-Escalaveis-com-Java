package br.edu.infnet.infra;

import br.edu.infnet.domain.Telefone;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class TelefoneConverter implements AttributeConverter<Telefone, String> {
    @Override
    public String convertToDatabaseColumn(Telefone fone) {
        return new StringBuilder()
                .append("(")
                .append(fone.getDdd())
                .append(") ")
                .append(fone.getNumero())
                .append(" ")
                .append(fone.getTipo().toString())
                .toString();
    }

    @Override
    public Telefone convertToEntityAttribute(String fone) {
        String[] campos = fone.split(" ");
        int  ddd = Integer.parseInt(campos[0].replace("(","").replace(")",""));
        int numero = Integer.parseInt(campos[1]);
        String tipo = Telefone.TipoTelefone.CELULAR.toString();

        if(campos.length == 3){
            tipo = campos[2];
        }

        return new Telefone.Builder()
                .addDdd(ddd)
                .addNumero(numero)
                .addTipoTelefone(Telefone.TipoTelefone.valueOf(tipo))
                .build();
    }
}
