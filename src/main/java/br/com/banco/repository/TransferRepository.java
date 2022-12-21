package br.com.banco.repository;

import br.com.banco.entity.Transference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transference, Long> {
}
