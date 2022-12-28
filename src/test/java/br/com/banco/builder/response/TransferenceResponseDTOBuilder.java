package br.com.banco.builder.response;

import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.entity.Account;
import br.com.banco.entity.Transference;
import br.com.banco.enums.TransferenceTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
public class TransferenceResponseDTOBuilder {

    @Builder.Default
    private Long id = 1l;

    @Builder.Default
    private BigDecimal value = BigDecimal.valueOf(1000000.99);

    @Builder.Default
    private LocalDateTime transferenceDate = LocalDateTime.parse("2020-05-04T02:12:45");

    @Builder.Default
    private String type = "TRANSFERENCIA";

    @Builder.Default
    private String transferOperationName = "Asdrubal";

    @Builder.Default
    private Account account = new Account(1L, "Manuela");

    public TransferenceResponseDTO toTransferenceResponseDTO() {
        return new TransferenceResponseDTO(
                id,
                value,
                transferenceDate,
                type,
                transferOperationName,
                account
        );
    }

}
