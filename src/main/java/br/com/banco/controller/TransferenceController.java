package br.com.banco.controller;

import br.com.banco.dto.FilterDTO;
import br.com.banco.dto.TransferenceDTO;
import br.com.banco.service.TransferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class TransferenceController {

    private TransferenceService transferenceService;

    @GetMapping("/{accountNr}")
    public List<TransferenceDTO> getAllTransferenceByAccountId(@PathVariable final Long accountNr,
                                                                @RequestBody(required = false) final FilterDTO filterDTO) {
        return transferenceService.getAllTransferenceByAccountId(accountNr, filterDTO);
    }

}
