package br.com.banco.service;

import br.com.banco.dto.FilterDTO;
import br.com.banco.dto.TransferenceDTO;
import br.com.banco.entity.Transference;
import br.com.banco.mapper.TransferenceMapper;
import br.com.banco.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransferenceService {

    private TransferRepository transferRepository;
    private final TransferenceMapper transferenceMapper = TransferenceMapper.INSTANCE;

    public List<TransferenceDTO> getAllTransferenceByAccountId(final Long accountNr, final FilterDTO filterDTO) {
        if (filterDTO == null) {
            List<Transference> allTransference = transferRepository.findAllByAccountId(accountNr);
            return allTransference.stream()
                    .map(transferenceMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return getAllTransferenceByFilter(accountNr, filterDTO);
    }

    public List<TransferenceDTO> getAllTransferenceByFilter(final Long accountNr, final FilterDTO filterDTO) {
        LocalDateTime startDate = filterDTO.getInitialFilterDate();
        LocalDateTime endDate = filterDTO.getEndFilterDate();
        String transferOperationalName = filterDTO.getTransferOperationName();

        List<Transference> allTransference = transferRepository.findByFilter(accountNr, startDate, endDate, transferOperationalName);

        return allTransference.stream()
                .map(transferenceMapper::toDTO)
                .collect(Collectors.toList());

    }

}
