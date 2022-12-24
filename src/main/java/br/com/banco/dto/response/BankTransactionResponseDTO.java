package br.com.banco.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionResponseDTO {

    @NotNull
    List<TransferenceResponseDTO> transferenceList;

    @NotNull
    private BigDecimal totalBalance;
}
