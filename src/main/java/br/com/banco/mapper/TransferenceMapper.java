package br.com.banco.mapper;

import br.com.banco.dto.TransferenceDTO;
import br.com.banco.entity.Transference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferenceMapper {

    TransferenceMapper INSTANCE = Mappers.getMapper(TransferenceMapper.class);

    @Mapping(source="type", target="type")
    Transference toModel(TransferenceDTO transferenceDTO);

    @Mapping(source="type", target="type")
    TransferenceDTO toDTO(Transference transference);
}
