package br.com.banco.exeption;

import br.com.banco.dto.response.ExceptionDto;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransferenceNotFoundException.class)
    public ResponseEntity<ExceptionDto> transferenceNotFoundException(TransferenceNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }

    @ExceptionHandler(IncorrectDateException.class)
    public ResponseEntity<ExceptionDto> incorrectDateException(IncorrectDateException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
}
