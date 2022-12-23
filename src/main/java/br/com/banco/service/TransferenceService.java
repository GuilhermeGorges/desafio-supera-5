package br.com.banco.service;

import br.com.banco.dto.TransferenceDTO;
import br.com.banco.entity.Transference;
import br.com.banco.mapper.TransferenceMapper;
import br.com.banco.repository.AccountRepository;
import br.com.banco.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransferenceService {

    private TransferRepository transferRepository;
    private final TransferenceMapper transferenceMapper = TransferenceMapper.INSTANCE;

    public List<TransferenceDTO> getAllTransference() {
        List<Transference> allTransference = transferRepository.findAll();
        return allTransference.stream()
                .map(transferenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransferenceDTO> getAllTransferenceByAccountId(Long account) {
        List<Transference> allTransference = transferRepository.findAllByIdAccount(account);
        return allTransference.stream()
                    .map(transferenceMapper::toDTO)
                    .collect(Collectors.toList());
    }

}
