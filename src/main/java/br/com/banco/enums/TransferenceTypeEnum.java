package br.com.banco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransferenceTypeEnum {
    DEPOSITO("DEPOSITO"),
    SAQUE("SAQUE"),
    TRANSFERENCIA("TRANSFERENCIA");

    private final String description;

}
