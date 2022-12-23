package br.com.banco.enums;

import br.com.banco.entity.Transference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Getter
@AllArgsConstructor
public enum TransferType {
    DEPOSITO("DEPOSITO"),
    SAQUE("SAQUE"),
    TRANSFERENCIA("TRANSFERENCIA");

    private final String description;

    @Converter(autoApply = true)
    public static class Mapeador implements AttributeConverter<TransferType, String> {

        @Override
        public String convertToDatabaseColumn(TransferType x) {
            return String.valueOf(x.getDescription());
        }

        @Override
        public TransferType convertToEntityAttribute(String y) {
            if (y == null) return null;
            if ("DEPOSITO".equals(y)) return DEPOSITO;
            if ("SAQUE".equals(y)) return SAQUE;
            if ("TRANSFERENCIA".equals(y)) return TRANSFERENCIA;
            throw new IllegalStateException("Valor inválido: " + y);
        }
    }
}
