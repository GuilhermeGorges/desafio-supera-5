package br.com.banco.controller;

import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import br.com.banco.service.TransferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class TransferenceController {

    private TransferenceService transferenceService;

    @GetMapping(value = "/{accountNr}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransferenceResponseDTO>> getAllTransferenceByAccountId(@PathVariable final Long accountNr,
                                                                                      @RequestBody(required = false) final FilterRequestDTO filterRequestDTO) throws IncorrectDateException, TransferenceNotFoundException {

        return ResponseEntity.ok(transferenceService.getAllTransferenceByAccountId(accountNr, filterRequestDTO));
    }

}
