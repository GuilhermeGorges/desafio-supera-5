package br.com.banco.builder.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.banco.dto.request.FilterRequestDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FilterRequestDTOBuilder {

    @Builder.Default
    private LocalDateTime initialFilterDate = null; //LocalDateTime.parse("2019-05-04T02:12:45");
    @Builder.Default
    private LocalDateTime endFilterDate = null;// LocalDateTime.parse("2021-05-04T02:12:45");

    @Builder.Default
    private String transferOperationName = "Asdrubal";

    public FilterRequestDTO filterRequestDTO() {
        return new FilterRequestDTO(initialFilterDate,
                                    endFilterDate,
                                    transferOperationName);
    }
}
