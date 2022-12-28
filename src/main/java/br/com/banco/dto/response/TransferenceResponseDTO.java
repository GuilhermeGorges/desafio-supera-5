package br.com.banco.dto.response;

import br.com.banco.entity.Account;
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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenceResponseDTO {

    private Long id;

    @NotNull
    private BigDecimal value;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transferenceDate;

    @NotNull
    private String type;

    @Max(50)
    private String transferOperationName;

    @NotNull
    @Valid
    private Account account;

}
