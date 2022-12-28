package br.com.banco.controller;

import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import br.com.banco.service.TransferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


@RestController
@RequestMapping("/transference")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class TransferenceController {

    private TransferenceService transferenceService;

    @GetMapping(value = "/{accountNr}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankTransactionResponseDTO> getAllTransferenceByAccountId(@PathVariable final Long accountNr,
                                                                                    @RequestParam(value="filterRequestDTO", required = false) final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException {
        return ResponseEntity.ok(transferenceService.getAllTransferenceByAccountId(accountNr, filterRequestDTO));
    }

}
