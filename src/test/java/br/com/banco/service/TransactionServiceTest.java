package br.com.banco.service;

import br.com.banco.mapper.TransferenceMapper;
import br.com.banco.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    private static final long INVALID_TRANSACTION_ID = 1L;

    @Mock
    private TransferRepository transferRepository;

    private TransferenceMapper transferenceMapper = TransferenceMapper.INSTANCE;

    @InjectMocks
    private TransferenceService transferenceService;

}
