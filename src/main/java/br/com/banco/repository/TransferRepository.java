package br.com.banco.repository;

import br.com.banco.entity.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transference, Long> {

    @Query("from Transference where conta_id = :conta_id")
    List<Transference> findAllByAccountId(@Param("conta_id") Long account);

    @Query( "from Transference " +
            "where  conta_id = ?1 " +
            "and    (?2 is null or (data_transferencia >= ?2)) " +
            "and    (?3 is null or (data_transferencia <= ?3)) " +
            "and    (?4 is null or (nome_operador_transacao LIKE ?4))")
    List<Transference> findByFilter
            (final Long accountNr, final LocalDateTime startDate, final LocalDateTime endDate, final String transferOperationalName);


    @Query(value = "select sum(valor) from transferencia where conta_id = :conta_id", nativeQuery = true)
    BigDecimal findTotalAccountBalanceByAccountId(@Param("conta_id") Long account);
}
