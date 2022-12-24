package br.com.banco.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequestDTO {

    private LocalDateTime initialFilterDate;
    private LocalDateTime endFilterDate;

    @Max(50)
    private String transferOperationName;
}
