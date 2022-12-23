package br.com.banco.dto;

import br.com.banco.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenceDTO {

    private Long id;

    @NotNull
    private BigDecimal value;

    @NotNull
    private Timestamp date;

    @NotNull
    private String type;

    @Max(50)
    private String transferOperationName;

    @NotNull
    @Valid
    private Account account;
}
