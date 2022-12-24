package br.com.banco.service;

import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface TransferenceService {

    BankTransactionResponseDTO getAllTransferenceByAccountId(final Long accountNr, final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException;

}
