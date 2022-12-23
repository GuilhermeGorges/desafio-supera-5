package br.com.banco.controller;

import br.com.banco.dto.TransferenceDTO;
import br.com.banco.entity.Transference;
import br.com.banco.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class AccountController {

    private AccountService accountService;

    @GetMapping("/{id}")
    public List<TransferenceDTO> getAllAccountTransference(@PathVariable Long id) {
        return accountService.getAllAccountTransference(id);
    }

}
