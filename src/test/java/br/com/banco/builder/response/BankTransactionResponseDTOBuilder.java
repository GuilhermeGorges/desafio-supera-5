package br.com.banco.builder.response;

import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.entity.Account;
import br.com.banco.enums.TransferenceTypeEnum;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.banco.dto.response.TransferenceResponseDTO;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public class BankTransactionResponseDTOBuilder {

    @Builder.Default
    private List<TransferenceResponseDTO> transferenceList = getTransferenceList();

    @Builder.Default
    private BigDecimal totalExtractBalance = BigDecimal.valueOf(100000.99);

    @Builder.Default
    private BigDecimal totalAccountBalance = BigDecimal.valueOf(100000.99);

    private List<TransferenceResponseDTO> getTransferenceList() {
        List<TransferenceResponseDTO> transferenceList = new ArrayList<>();
        TransferenceResponseDTO transference = new TransferenceResponseDTO();
        transference.setId(1L);
        transference.setValue(BigDecimal.valueOf(100000.99));
        transference.setTransferenceDate(LocalDateTime.parse("2020-05-04T02:12:45"));
        transference.setType("TRANSFERENCIA");
        transference.setAccount(new Account(1L, "Maome"));
        transferenceList.add(transference);
        return transferenceList;
    }

    public BankTransactionResponseDTO toBankTransactionResponseDTO(){
        return new BankTransactionResponseDTO(
                transferenceList,
                totalExtractBalance,
                totalAccountBalance
        );
    }
}
