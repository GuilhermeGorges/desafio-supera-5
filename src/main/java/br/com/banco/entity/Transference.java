package br.com.banco.entity;

import br.com.banco.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transferencia")
public class Transference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @Column(name = "data_transferencia", nullable = false)
    private Timestamp date;

    @Column(name = "tipo", nullable = false)
    private TransferType type;

    @Column(name = "nome_operador_transacao")
    private String transferOperationName;

    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private Account account;

}
