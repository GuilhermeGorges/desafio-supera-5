package br.com.banco.service.impl;

import br.com.banco.dto.request.FilterRequestDTO;
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
    public List<TransferenceResponseDTO> getAllTransferenceByAccountId(final Long accountNr, final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException{
        if (filterRequestDTO == null) {
            List<Transference> allTransference = transferRepository.findAllByAccountId(accountNr);
            if(allTransference.isEmpty()) {
                throw new TransferenceNotFoundException();
            }
            return allTransference.stream()
                    .map(transferenceMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return getAllTransferenceByFilter(accountNr, filterRequestDTO);
    }

    @Transactional(readOnly = true)
    public List<TransferenceResponseDTO> getAllTransferenceByFilter(final Long accountNr, final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException {
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
        return allTransference.stream()
                .map(transferenceMapper::toDTO)
                .collect(Collectors.toList());

    }

    private void verifyStartAndEndDate(final LocalDateTime startDate, final LocalDateTime endDate) throws IncorrectDateException {
        if(endDate.isBefore(startDate)) {
            throw new IncorrectDateException();
        }
    }

}
