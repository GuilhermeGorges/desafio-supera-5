package br.com.banco.repository;

import br.com.banco.entity.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transference, Long> {

    @Query("from Transference where conta_id = :conta_id")
    List<Transference> findAllByIdAccount(@Param("conta_id") Long account);
}
