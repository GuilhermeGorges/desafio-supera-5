package br.com.banco.service.impl;

import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.entity.Transference;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import br.com.banco.mapper.TransferenceMapper;
import br.com.banco.repository.TransferRepository;
import br.com.banco.service.TransferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransferenceServiceImpl implements TransferenceService {

    private TransferRepository transferRepository;
    private final TransferenceMapper transferenceMapper = TransferenceMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public BankTransactionResponseDTO getAllTransferenceByAccountId(final Long accountNr, final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException{
        List<Transference> allTransference;

        if (filterRequestDTO.isFilterEmpty()) {
            allTransference = transferRepository.findAllByAccountId(accountNr);
            if(allTransference.isEmpty()) {
                throw new TransferenceNotFoundException();
            }
        } else {
            allTransference = getAllTransferenceByFilter(accountNr, filterRequestDTO);
        }
        return createBankTransactionResponseDTO(accountNr, allTransference);
    }

    private List<Transference> getAllTransferenceByFilter(final Long accountNr, final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException {
        LocalDateTime startDate = filterRequestDTO.getInitialFilterDate();
        LocalDateTime endDate = filterRequestDTO.getEndFilterDate();
        if (startDate != null && endDate != null) {
            verifyStartAndEndDate(startDate, endDate);
        }

        String transferOperationalName = filterRequestDTO.getTransferOperationName();

        List<Transference> allTransference = transferRepository.findByFilter(accountNr, startDate, endDate, transferOperationalName);

        if (allTransference.isEmpty()) {
            throw new TransferenceNotFoundException(filterRequestDTO);
        }
        return allTransference;

    }

    private void verifyStartAndEndDate(final LocalDateTime startDate, final LocalDateTime endDate) throws IncorrectDateException {
        if(endDate.isBefore(startDate)) {
            throw new IncorrectDateException();
        }
    }

    private BankTransactionResponseDTO createBankTransactionResponseDTO(final Long accountNr, final List<Transference> allTransference) {
        BankTransactionResponseDTO bankTransactionResponseDTO = new BankTransactionResponseDTO();

        List<TransferenceResponseDTO> transferenceResponseDTOList = allTransference.stream()
                .map(transferenceMapper::toDTO)
                .collect(Collectors.toList());

        bankTransactionResponseDTO.setTransferenceList(transferenceResponseDTOList);
        bankTransactionResponseDTO.setTotalExtractBalance(sumTotalBalance(allTransference));
        bankTransactionResponseDTO.setTotalAccountBalance(transferRepository.findTotalAccountBalanceByAccountId(accountNr));

        return bankTransactionResponseDTO;
    }

    private BigDecimal sumTotalBalance(final List<Transference> allTransference) {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for(Transference transference : allTransference) {
            totalBalance = totalBalance.add(transference.getValue());
        }
        totalBalance = totalBalance.setScale(2, RoundingMode.HALF_EVEN);
        return totalBalance;
    }
}
