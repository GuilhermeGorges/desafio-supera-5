package br.com.banco.exeption.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectDateException extends Throwable {
    public IncorrectDateException() {
        super("A data final deve ser posterior a data inicial");
    }
}