package br.com.banco.service;

import br.com.banco.dto.TransferenceDTO;
import br.com.banco.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private AccountRepository accountRepository;

    public List<TransferenceDTO> getAllAccountTransference(Long id) {
        List<TransferenceDTO> allTransference = accountRepository.findAllById()
    }
}
