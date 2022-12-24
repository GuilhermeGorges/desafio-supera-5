package br.com.banco.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequestDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime initialFilterDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime endFilterDate;

    @Max(50)
    private String transferOperationName;
}
