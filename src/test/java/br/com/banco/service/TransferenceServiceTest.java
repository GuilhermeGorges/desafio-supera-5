package br.com.banco.service;

import br.com.banco.builder.request.FilterRequestDTOBuilder;
import br.com.banco.builder.response.BankTransactionResponseDTOBuilder;
import br.com.banco.builder.response.TransferenceResponseDTOBuilder;
import br.com.banco.dto.request.FilterRequestDTO;
import br.com.banco.dto.response.BankTransactionResponseDTO;
import br.com.banco.dto.response.TransferenceResponseDTO;
import br.com.banco.entity.Transference;
import br.com.banco.exeption.exeptions.IncorrectDateException;
import br.com.banco.exeption.exeptions.TransferenceNotFoundException;
import br.com.banco.mapper.TransferenceMapper;
import br.com.banco.repository.TransferRepository;
import br.com.banco.service.impl.TransferenceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TransferenceServiceTest {

    private static final String TRANSFERENCE_API_URL_PATH = "/transference";
    private static final long INVALID_TRANSACTION_ID = 99L;

    @Mock
    private TransferRepository transferRepository;

    private TransferenceMapper transferenceMapper = TransferenceMapper.INSTANCE;

    private MockMvc mockMvc;

    @InjectMocks
    private TransferenceServiceImpl transferenceServiceImpl = new TransferenceServiceImpl(transferRepository);

    @Test
    void whenListTransferenceResponseDTOIsCalledThenReturnListOfTransference() {
        //given
        TransferenceResponseDTO expectedFoundTransferenceResponseDTO = TransferenceResponseDTOBuilder.builder().build().toTransferenceResponseDTO();
        Transference expectedFoundTransference = transferenceMapper.toModel(expectedFoundTransferenceResponseDTO);

        //when
        when(transferRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundTransference));

        //then
        List<TransferenceResponseDTO> foundListTransferenceResponseDTO = transferenceServiceImpl.getAllTransference();

        assertThat(foundListTransferenceResponseDTO, is(not(empty())));
        assertThat(foundListTransferenceResponseDTO.get(0), is(equalTo(expectedFoundTransferenceResponseDTO)));

    }

    @Test
    void whenGETIsCalledWithIdAndFilterValidBankTransactionResponseDTOReturned() throws IncorrectDateException, TransferenceNotFoundException, Exception {
        //given
        BankTransactionResponseDTO bankTransactionResponseDTO = BankTransactionResponseDTOBuilder.builder().build().toBankTransactionResponseDTO();
        FilterRequestDTO filterRequestDTO = FilterRequestDTOBuilder.builder().build().filterRequestDTO();


        Long accountID = bankTransactionResponseDTO.getTransferenceList().get(0).getAccount().getId();
        //when
        when(transferenceServiceImpl.getAllTransferenceByAccountIdAndFilter(accountID, filterRequestDTO));
        //then
        mockMvc.perform(MockMvcRequestBuilders.get(TRANSFERENCE_API_URL_PATH + "/" + accountID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transferenceList",is(bankTransactionResponseDTO.getTransferenceList())))
                .andExpect(jsonPath("$.totalAccountBalance",is(bankTransactionResponseDTO.getTotalAccountBalance())))
                .andExpect(jsonPath("$.totalExtractBalance",is(bankTransactionResponseDTO.getTotalExtractBalance())));

    }

}
