package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;
}
