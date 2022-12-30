package br.com.banco.controller;

import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import br.com.banco.service.TransferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


@RestController
@RequestMapping("/transference")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class TransferenceController {

    private TransferenceService transferenceService;

    @GetMapping(value = "/{accountNr}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankTransactionResponseDTO> getAllTransferenceByAccountIdAndFilter(@PathVariable final Long accountNr,
                                                                                            @ModelAttribute final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException {
        return ResponseEntity.ok(transferenceService.getAllTransferenceByAccountIdAndFilter(accountNr, filterRequestDTO));
    }

    @GetMapping
    private List<TransferenceResponseDTO> getAllTransference(){
        return transferenceService.getAllTransference();
    }

}
