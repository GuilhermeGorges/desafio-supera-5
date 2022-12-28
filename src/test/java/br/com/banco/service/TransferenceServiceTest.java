package br.com.banco.service;

import br.com.banco.builder.response.TransferenceResponseDTOBuilder;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.entity.Transference;
import br.com.banco.mapper.TransferenceMapper;
import br.com.banco.repository.TransferRepository;
import br.com.banco.service.impl.TransferenceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TransferenceServiceTest {

    private static final long INVALID_TRANSACTION_ID = 2L;

    @Mock
    private TransferRepository transferRepository;

    private TransferenceMapper transferenceMapper = TransferenceMapper.INSTANCE;

    @InjectMocks
    private TransferenceService transferenceService = new TransferenceServiceImpl(transferRepository);

    @Test
    void whenListTransferenceResponseDTOIsCalledThenReturnListOfTransference() {
        //given
        TransferenceResponseDTO expectedFoundTransferenceResponseDTO = TransferenceResponseDTOBuilder.builder().build().toTransferenceResponseDTO();
        Transference expectedFoundTransference = transferenceMapper.toModel(expectedFoundTransferenceResponseDTO);

        //when
        when(transferRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundTransference));

        //then
        List<TransferenceResponseDTO> foundListTransferenceResponseDTO = transferenceService.getAllTransference();

        assertThat(foundListTransferenceResponseDTO, is(not(empty())));
        assertThat(foundListTransferenceResponseDTO.get(0), is(equalTo(expectedFoundTransferenceResponseDTO)));

    }

}
