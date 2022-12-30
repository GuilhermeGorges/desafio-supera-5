package br.com.banco.service;

import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransferenceService {

    BankTransactionResponseDTO getAllTransferenceByAccountIdAndFilter(final Long accountNr, final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException;

    List<TransferenceResponseDTO> getAllTransference();

}
