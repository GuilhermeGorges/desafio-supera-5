package br.com.banco.mapper;

import br.com.banco.dto.TransferenceDTO;
import br.com.banco.entity.Transference;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferenceMapper {

    TransferenceMapper INSTANCE = Mappers.getMapper(TransferenceMapper.class);

    Transference toModel(TransferenceDTO transferenceDTO);

    TransferenceDTO toDTO(Transference transference);
}
